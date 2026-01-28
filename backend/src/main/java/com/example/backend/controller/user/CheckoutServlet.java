package com.example.backend.controller.user;

import com.example.backend.dao.OrderDao;
import com.example.backend.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final String POST_LOGIN_REDIRECT_KEY = "postLoginRedirect";
    private static final String CHECKOUT_FORM_SESSION_KEY = "checkoutForm";
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

        cacheCheckoutForm(session, email, fullName, phone, address, province, district, ward, note);
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
            session.setAttribute(POST_LOGIN_REDIRECT_KEY, request.getContextPath() + "/checkout");
            response.sendRedirect(request.getContextPath() + "/login");
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
        order.setTotal_amount(cart.getTotalMoney()+30000);

        // call Dao
        OrderDao orderDao = new OrderDao();
        String url = "/checkout.jsp";
        try {
            int orderId = orderDao.saveOrder(order,cart);

            // Nếu orderId lớn hơn 0 nghĩa là thực hiện thanh toán thành công và thực hiện xóa giỏ hàng
            if(orderId>0){
                List<OrderItem> orderItems = orderDao.getOrderItems(orderId);
                session.removeAttribute("cart");
                session.removeAttribute(CHECKOUT_FORM_SESSION_KEY);

                request.setAttribute("order",order);
                request.setAttribute("orderId",orderId);
                request.setAttribute("orderedItems",orderItems);
                request.setAttribute("customerEmail",email);

                url = "/order-success.jsp";
                //request.getRequestDispatcher("/order-success.jsp").forward(request,response);
            }
            else{
                request.setAttribute("ERROR","Đặt hàng thất bại");
                //request.getRequestDispatcher("checkout.jsp").forward(request,response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR","Lỗi hệ thống: "+e.getMessage());
            //request.getRequestDispatcher("checkout.jsp").forward(request,response);
        }
        request.getRequestDispatcher(url).forward(request,response);

    }

    private void cacheCheckoutForm(HttpSession session, String email, String fullName, String phone,
                                   String address, String province, String district, String ward, String note) {
        Map<String, String> formData = new HashMap<>();
        putIfPresent(formData, "email", email);
        putIfPresent(formData, "fullname", fullName);
        putIfPresent(formData, "phone", phone);
        putIfPresent(formData, "address", address);
        putIfPresent(formData, "province", province);
        putIfPresent(formData, "district", district);
        putIfPresent(formData, "ward", ward);
        putIfPresent(formData, "note", note);

        if (!formData.isEmpty()) {
            session.setAttribute(CHECKOUT_FORM_SESSION_KEY, formData);
        }
    }

    private void putIfPresent(Map<String, String> formData, String key, String value) {
        if (value != null) {
            String trimmed = value.trim();
            if (!trimmed.isEmpty()) {
                formData.put(key, trimmed);
            }
        }
    }
}