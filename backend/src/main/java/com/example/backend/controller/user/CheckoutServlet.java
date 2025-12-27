package com.example.backend.controller.user;

import com.example.backend.dao.OrderDao;
import com.example.backend.model.Cart;
import com.example.backend.model.Order;
import com.example.backend.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("checkout.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //Nhận vào tiếng việt

        //Lấy giỏ hàng từ ss
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if(cart == null || cart.getTotalQuantity() ==0){
            response.sendRedirect("shopping-cart.jsp");
            return;
        }

        // Lấy thông tin người dùng
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String province = request.getParameter("province"); // Nếu muốn gộp vào address
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String note = request.getParameter("note");

        String fullAddress = address;
        if (ward != null) fullAddress += ", " + ward;
        if (district != null) fullAddress += ", " + district;
        if (province != null) fullAddress += ", " + province;

        // Lấy user id
        // Nếu đăng nhập vào rồi thì được mua nếu chưa đăng nhập thì buộc phải đăng nhập mới được mua
        User user = (User) session.getAttribute("user");
        int userId = 0;
        if(user!=null){
            userId = user.getId();
        }
        else{
            response.sendRedirect("login.jsp");
            return;
        }

        // Object Order
        Order order = new Order();
        order.setUser_id(userId);
        order.setShipping_name(fullName);
        order.setShipping_phone(phone);
        order.setShipping_address(fullAddress);
        order.setShipping_fee(30000);
        order.setNote(note);
        // Tiền hàng cộng thêm tiền ship nữa
        order.setTotal_amount(cart.getTotal()+30000);

        // call Dao
        OrderDao orderDao = new OrderDao();
        try {
            int orderId = orderDao.saveOrder(order,cart);

            // Nếu orderId lớn hơn 0 nghĩa là thực hiện thanh toán thành công và thực hiện xóa giỏ hàng
            if(orderId>0){
                session.removeAttribute("cart");

                request.setAttribute("order",order);
                request.setAttribute("orderId",orderId);
                request.getRequestDispatcher("order-success.jsp").forward(request,response);
            }
            else{
                request.setAttribute("ERROR","Đặt hàng thất bại");
                request.getRequestDispatcher("checkout.jsp").forward(request,response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR"," "+e.getMessage());
            request.getRequestDispatcher("checkout.jsp").forward(request,response);
        }
    }
}