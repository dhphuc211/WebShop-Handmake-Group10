package com.example.backend.controller.user;

import com.example.backend.dao.UserDAO;
import com.example.backend.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * UserProfileServlet - Quản lý thông tin cá nhân
 * URL: /profile
 */
@WebServlet("/profile")
public class UserProfileServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }


     //Hiển thị trang thông tin cá nhân
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy user từ session
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser != null) {
            // Lấy thông tin mới nhất từ DB
            User userFromDB = userDAO.getUserById(currentUser.getId());
            
            if (userFromDB != null) {
                session.setAttribute("user", userFromDB);
                request.setAttribute("user", userFromDB);
            }
        }

        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    //Cập nhật thông tin cá nhân
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        // Nếu có user thì thực hiện cập nhật
        if (currentUser != null) {
            // Lấy dữ liệu từ form
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            
            // Validate
            if (fullName != null && !fullName.trim().isEmpty() && 
                phone != null && !phone.trim().isEmpty()) {
                
                // Cập nhật thông tin vào object User
                currentUser.setFullName(fullName.trim());
                currentUser.setPhone(phone.trim());

                // Gọi DAO để lưu xuống DB
                boolean success = userDAO.updateProfile(currentUser);

                if (success) {
                    session.setAttribute("user", currentUser);
                    session.setAttribute("userName", currentUser.getFullName());
                    request.setAttribute("successMessage", "Cập nhật thông tin thành công!");
                } else {
                    request.setAttribute("errorMessage", "Đã có lỗi xảy ra. Vui lòng thử lại!");
                }
            } else {
                 request.setAttribute("errorMessage", "Vui lòng không để trống họ tên và số điện thoại!");
            }
        }

        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}
