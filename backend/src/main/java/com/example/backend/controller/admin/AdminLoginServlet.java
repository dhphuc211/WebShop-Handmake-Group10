package com.example.backend.controller.admin;

import com.example.backend.dao.UserDAO;
import com.example.backend.model.User;
import com.example.backend.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AdminLoginServlet", value = "/admin-login")
public class AdminLoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/admin/admin-login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Vui long nhap day du thong tin.");
            request.getRequestDispatcher("/admin/admin-login.jsp").forward(request, response);
            return;
        }

        String hashedPassword = PasswordUtil.encrypt(password);
        User user = userDAO.checkLogin(username.trim(), hashedPassword);

        if (user == null) {
            request.setAttribute("errorMessage", "Thong tin dang nhap khong dung.");
            request.getRequestDispatcher("/admin/admin-login.jsp").forward(request, response);
            return;
        }

        if (!user.isAdmin()) {
            request.setAttribute("errorMessage", "Tai khoan khong co quyen admin.");
            request.getRequestDispatcher("/admin/admin-login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        session.setAttribute("userId", user.getId());
        session.setAttribute("userName", user.getFullName());
        session.setAttribute("userRole", user.getRole());
        session.setMaxInactiveInterval(30 * 60);

        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
    }
}
