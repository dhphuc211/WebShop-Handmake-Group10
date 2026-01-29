package com.example.backend.controller.admin;

import com.example.backend.dao.OrderDao;
import com.example.backend.model.Order;
import com.example.backend.model.OrderItem;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrderServlet", value = "/admin/orders")
public class AdminOrderServlet extends HttpServlet {

    private OrderDao orderDao = new OrderDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "detail":
                viewOrderDetail(request, response);
                break;
            case "list":
            default:
                viewOrderList(request, response);
                break;
        }
    }

    // xem danh sách đơn hàng
    private void viewOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int pageSize = 8;
        try {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            if (request.getParameter("pageSize") != null) {
                pageSize = Integer.parseInt(request.getParameter("pageSize"));
            }
        } catch (NumberFormatException e) {
            page = 1;
        }
        List<Order> orders = orderDao.getAllOrders(); // Tạm thời lấy hết nếu chưa sửa DAO

        int totalOrders = orders.size(); // Thay bằng hàm count() trong DAO nếu dữ liệu lớn
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        request.setAttribute("orders", orders);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalOrders", totalOrders);

        request.getRequestDispatcher("/admin/orders/order-list.jsp").forward(request, response);    }

    // xem chi tiết đơn hàng
    private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Order order = orderDao.getOrderById(id); // Lấy thông tin đơn hàng
            List<OrderItem> details = orderDao.getOrderItems(id); // Lấy danh sách sản phẩm trong đơn hàng

            if (order != null) {
                request.setAttribute("order", order);
                request.setAttribute("details", details);
                // Đảm bảo đường dẫn này đúng với vị trí file JSP của bạn
                request.getRequestDispatcher("/admin/orders/order-detail.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/orders");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/orders");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}