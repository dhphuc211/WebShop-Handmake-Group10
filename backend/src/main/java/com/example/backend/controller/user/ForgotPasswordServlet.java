package com.example.backend.controller.user;

import com.example.backend.dao.UserDAO;
import com.example.backend.model.User;
import com.example.backend.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String emailOrPhone = request.getParameter("emailOrPhone");
        if (emailOrPhone == null || emailOrPhone.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng nhập email hoặc số điện thoại.");
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
            return;
        }

        String key = emailOrPhone.trim();
        request.setAttribute("emailOrPhone", key);

        User user = userDAO.findByEmailOrPhone(key);
        if (user == null) {
            request.setAttribute("errorMessage", "Không tìm thấy tài khoản phù hợp.");
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
            return;
        }

        if (!user.isActive()) {
            request.setAttribute("errorMessage", "Tài khoản đang bị khóa.");
            request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
            return;
        }

        String newPassword = PasswordUtil.generateRandomPassword(8);
        String hashedPassword = PasswordUtil.encrypt(newPassword);

        boolean updated = userDAO.updatePassword(user.getId(), hashedPassword);
        if (updated) {
            request.setAttribute("successMessage", "Đặt lại mật khẩu thành công. Vui lòng đăng nhập bằng mật khẩu mới.");
            request.setAttribute("generatedPassword", newPassword);
        } else {
            request.setAttribute("errorMessage", "Không thể đặt lại mật khẩu. Vui lòng thử lại.");
        }

        request.getRequestDispatcher("/forgot-password.jsp").forward(request, response);
    }
}
