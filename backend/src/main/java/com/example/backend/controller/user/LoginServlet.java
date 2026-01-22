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
            response.sendRedirect(request.getContextPath() + "/index");
            return;
        }

        // Chuyển đến trang login.jsp
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * Xử lý POST request - Xử lý form đăng nhập
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Thiết lập encoding để đọc tiếng Việt
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String emailOrPhone = request.getParameter("emailOrPhone");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        // Validate dữ liệu
        // Kiểm tra dữ liệu không được rỗng
        if (emailOrPhone == null || emailOrPhone.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {

            // Gửi thông báo lỗi về trang login
            request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        //Kiểm tra đăng nhập
        // Mã hóa password trước khi kiểm tra
        String hashedPassword = PasswordUtil.encrypt(password);
        
        User user = userDAO.checkLogin(emailOrPhone, hashedPassword);

        if (user != null && !user.isActive()) {
            request.setAttribute("errorMessage", "Tài khoản đang bị khóa!");
            request.setAttribute("emailOrPhone", emailOrPhone);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (user != null) {
            // ĐĂNG NHẬP THÀNH CÔNG

            // Tạo session mới cho user
            HttpSession session = request.getSession(true);

            // Lưu thông tin user vào session
            // Session là nơi lưu trữ thông tin user trong suốt phiên làm việc
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getFullName());
            session.setAttribute("userRole", user.getRole());

            // Thiết lập thời gian timeout cho session (30 phút)
            session.setMaxInactiveInterval(30 * 60);

            // Log để debug
            System.out.println("✓ Đăng nhập thành công: " + user.getEmail());

            // User login luon ve trang chu (admin chi vao dashboard khi dang nhap trang admin)
            response.sendRedirect(request.getContextPath() + "/index");

        } else {
            // ĐĂNG NHẬP THẤT BẠI

            System.out.println("✗ Đăng nhập thất bại: " + emailOrPhone);

            // Gửi thông báo lỗi
            request.setAttribute("errorMessage", "Email/SĐT hoặc mật khẩu không đúng!");

            // Giữ lại email/phone đã nhập để user không phải nhập lại
            request.setAttribute("emailOrPhone", emailOrPhone);

            // Quay lại trang login
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}