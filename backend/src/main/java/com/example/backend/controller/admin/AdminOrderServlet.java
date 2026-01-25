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
        List<Order> orders = orderDao.getAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/admin/orders/order-list.jsp").forward(request, response);
    }

    // xem chi tiết đơn hàng
    private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Order order = orderDao.getOrderById(id);
            List<OrderItem> details = orderDao.getOrderItems(id);

            if (order != null) {
                request.setAttribute("order", order);
                request.setAttribute("details", details);
                request.getRequestDispatcher("/admin/order-detail.jsp").forward(request, response);
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