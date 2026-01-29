package com.example.backend.controller.user;

import com.example.backend.dao.OrderDao;
import com.example.backend.dao.ProductDAO;
import com.example.backend.dao.ReviewDAO;
import com.example.backend.model.*;
import com.example.backend.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/order-review")
public class ReviewServlet extends HttpServlet {
    private ReviewDAO reviewDAO = new ReviewDAO();
    private OrderDao orderDAO = new OrderDao();
    private ProductDAO productDAO = new ProductDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy thông tin user từ Session (giả sử bạn đã lưu user khi đăng nhập)
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy dữ liệu từ form
        int productId = Integer.parseInt(request.getParameter("productId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        // Tạo đối tượng Review
        Review review = new Review();
        review.setUserId(user.getId());
        review.setProductId(productId);
        review.setRating(rating);
        review.setComment(comment);

        // Lưu xuống DB
        boolean success = reviewDAO.insertReview(review);

        if (success) {
            // Chuyển hướng đến trang thành công hoặc quay lại đơn hàng
            response.sendRedirect("review-success.jsp");
        } else {
            request.setAttribute("error", "Không thể gửi đánh giá, vui lòng thử lại!");
            request.getRequestDispatcher("order-review.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mặc định luôn hiển thị form đánh giá nếu gọi vào servlet này bằng GET
        showReviewForm(request, response);
    }

    private void showReviewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String orderIdStr = request.getParameter("orderId");
        Order order = null;

        if (orderIdStr != null && !orderIdStr.isEmpty()) {
            order = orderDAO.getOrderById(Integer.parseInt(orderIdStr));
        } else {
            // TỰ ĐỘNG LẤY ĐƠN HÀNG MỚI NHẤT CỦA USER VỪA MUA
            // Bạn cần thêm hàm getLatestOrderByUserId vào OrderDao
            order = orderDAO.getLatestOrderByUserId(user.getId());
        }

        if (order != null) {
            List<OrderItem> orderItems = orderDAO.getOrderItems(order.getId());
            request.setAttribute("order", order);
            request.setAttribute("orderItems", orderItems);
            request.getRequestDispatcher("/order-review.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
