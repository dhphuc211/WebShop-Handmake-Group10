package com.example.backend.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LogoutServlet - Xử lý đăng xuất
 * URL: /logout
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Lấy session hiện tại (nếu không có thì trả về null)
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Xóa session (hủy đăng nhập)
            session.invalidate();
        }
        // Chuyển hướng về trang chủ (hoặc trang login)
        response.sendRedirect(request.getContextPath() + "/");
    }
}
