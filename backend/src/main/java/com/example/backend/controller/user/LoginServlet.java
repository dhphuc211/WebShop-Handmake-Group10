package com.example.backend.controller.user;

import com.example.backend.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * LoginServlet - Xử lý chức năng đăng nhập
 *
 * URL Mapping: /login
 *
 * Luồng xử lý:
 * 1. GET  /login -> Hiển thị trang đăng nhập (login.jsp)
 * 2. POST /login -> Xử lý form đăng nhập
 *    - Thành công: Lưu user vào session, chuyển về trang chủ
 *    - Thất bại: Hiển thị lỗi, quay lại trang login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // DAO để truy cập database
    private UserDAO userDAO;

    // Khởi tạo servlet - chạy 1 lần khi servlet được load
    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    //Xử lý GET request - Hiển thị trang đăng nhập
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Kiểm tra nếu user đã đăng nhập rồi thì chuyển về trang chủ
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        // Chuyển đến trang login.jsp
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}