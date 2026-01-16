package com.example.backend.controller.user;

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

@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getRequestDispatcher("/account/account-change-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (currentPassword == null || currentPassword.trim().isEmpty()
                || newPassword == null || newPassword.trim().isEmpty()
                || confirmPassword == null || confirmPassword.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("/account/account-change-password.jsp").forward(request, response);
            return;
        }

        if (!PasswordUtil.isValidPassword(newPassword)) {
            request.setAttribute("errorMessage", "Mật khẩu mới phải có ít nhất 6 ký tự.");
            request.getRequestDispatcher("/account/account-change-password.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Mật khẩu xác nhận không khớp.");
            request.getRequestDispatcher("/account/account-change-password.jsp").forward(request, response);
            return;
        }

        User userFromDb = userDAO.getUserById(currentUser.getId());
        if (userFromDb == null) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String hashedCurrent = PasswordUtil.encrypt(currentPassword);
        if (!hashedCurrent.equals(userFromDb.getPassword())) {
            request.setAttribute("errorMessage", "Mật khẩu hiện tại không đúng.");
            request.getRequestDispatcher("/account/account-change-password.jsp").forward(request, response);
            return;
        }

        String hashedNew = PasswordUtil.encrypt(newPassword);
        if (hashedNew.equals(hashedCurrent)) {
            request.setAttribute("errorMessage", "Mật khẩu mới phải khác mật khẩu hiện tại.");
            request.getRequestDispatcher("/account/account-change-password.jsp").forward(request, response);
            return;
        }

        boolean updated = userDAO.updatePassword(userFromDb.getId(), hashedNew);
        if (updated) {
            userFromDb.setPassword(hashedNew);
            session.setAttribute("user", userFromDb);
            request.setAttribute("successMessage", "Đổi mật khẩu thành công.");
        } else {
            request.setAttribute("errorMessage", "Không thể đổi mật khẩu. Vui lòng thử lại.");
        }

        request.getRequestDispatcher("/account/account-change-password.jsp").forward(request, response);
    }
}
