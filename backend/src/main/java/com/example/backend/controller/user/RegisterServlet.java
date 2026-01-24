package com.example.backend.controller.user;

import com.example.backend.dao.UserDAO;
import com.example.backend.model.User;
import com.example.backend.util.CheckoutSessionUtil;
import com.example.backend.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * RegisterServlet - Xử lý chức năng đăng ký tài khoản
 *
 * URL Mapping: /register
 *
 * Luồng xử lý:
 * 1. GET  /register -> Hiển thị trang đăng ký (register.jsp)
 * 2. POST /register -> Xử lý form đăng ký
 *    - Validate dữ liệu
 *    - Kiểm tra email/phone đã tồn tại chưa
 *    - Tạo tài khoản mới
 *    - Chuyển đến trang login với thông báo thành công
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    /**
     * GET - Hiển thị trang đăng ký
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    /**
     * POST - Xử lý form đăng ký
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Thiết lập encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String agreeTerms = request.getParameter("agreeTerms");

        // Validate dữ liệu

        // Kiểm tra các trường bắt buộc
        if (isNullOrEmpty(fullName) || isNullOrEmpty(email) ||
                isNullOrEmpty(phone) || isNullOrEmpty(password)) {

            setErrorAndForward(request, response, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        // Kiểm tra đồng ý điều khoản
        if (agreeTerms == null) {
            setErrorAndForward(request, response, "Bạn cần đồng ý với điều khoản sử dụng!");
            return;
        }

        // Kiểm tra định dạng email
        if (!isValidEmail(email)) {
            setErrorAndForward(request, response, "Email không hợp lệ!");
            return;
        }

        // Kiểm tra định dạng số điện thoại (10-11 số)
        if (!isValidPhone(phone)) {
            setErrorAndForward(request, response, "Số điện thoại không hợp lệ (10-11 số)!");
            return;
        }

        // Kiểm tra độ dài mật khẩu
        if (!PasswordUtil.isValidPassword(password)) {
            setErrorAndForward(request, response, "Mật khẩu phải có ít nhất 6 ký tự!");
            return;
        }

        // Kiểm tra mật khẩu xác nhận
        if (!password.equals(confirmPassword)) {
            setErrorAndForward(request, response, "Mật khẩu xác nhận không khớp!");
            return;
        }

        //Kiểm tra trùng lặp

        //Kiểm tra email đã tồn tại
        if (userDAO.isEmailExists(email.trim())) {
            setErrorAndForward(request, response, "Email này đã được sử dụng!");
            return;
        }

        //Kiểm tra phone đã tồn tại
        if (userDAO.isPhoneExists(phone.trim())) {
            setErrorAndForward(request, response, "Số điện thoại này đã được sử dụng!");
            return;
        }

        // Tạo tài khoản mới
        // Mã hóa mật khẩu trước khi lưu
        String hashedPassword = PasswordUtil.encrypt(password);

        // Tạo đối tượng User
        User newUser = new User(
                fullName.trim(),
                email.trim().toLowerCase(),  // Email luôn lowercase
                phone.trim(),
                hashedPassword  // Lưu mật khẩu đã mã hóa
        );

        // Gọi DAO để lưu vào database
        boolean success = userDAO.register(newUser);

        if (success) {
            //ĐĂNG KÝ THÀNH CÔNG
            System.out.println("Đăng ký thành công: " + email);

            HttpSession session = request.getSession();
            String redirectUrl = (String) session.getAttribute("postLoginRedirect");
            if (redirectUrl != null && !redirectUrl.trim().isEmpty()) {
                User user = userDAO.checkLogin(email.trim().toLowerCase(), hashedPassword);
                if (user != null) {
                    session.setAttribute("user", user);
                    session.setAttribute("userId", user.getId());
                    session.setAttribute("userName", user.getFullName());
                    session.setAttribute("userRole", user.getRole());
                    session.setMaxInactiveInterval(30 * 60);
                    int orderId = CheckoutSessionUtil.placeOrderFromSession(session);
                    session.removeAttribute("postLoginRedirect");
                    if (orderId > 0) {
                        response.sendRedirect(request.getContextPath() + "/order-success.jsp");
                        return;
                    }
                    response.sendRedirect(request.getContextPath() + "/checkout");
                    return;
                }
            }
            // Chuyển đến trang login với thông báo thành công
            request.setAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        } else {
            // ĐĂNG KÝ THẤT BẠI
            System.out.println("✗ Đăng ký thất bại: " + email);

            setErrorAndForward(request, response, "Đăng ký thất bại! Vui lòng thử lại.");
        }
    }

    //PHƯƠNG THỨC HỖ TRỢ

    /**
     * Kiểm tra chuỗi null hoặc rỗng
     */
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Kiểm tra định dạng email đơn giản
     * Email hợp lệ: có @ và có dấu chấm sau @
     */
    private boolean isValidEmail(String email) {
        if (email == null) return false;
        // Regex đơn giản cho email
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    /**
     * Kiểm tra số điện thoại (10-11 chữ số)
     */
    private boolean isValidPhone(String phone) {
        if (phone == null) return false;
        // Chỉ chứa số, độ dài 10-11
        return phone.matches("^[0-9]{10,11}$");
    }

    /**
     * Gửi thông báo lỗi và quay lại trang đăng ký
     */
    private void setErrorAndForward(HttpServletRequest request,
                                    HttpServletResponse response,
                                    String errorMessage)
            throws ServletException, IOException {

        // Lưu thông báo lỗi
        request.setAttribute("errorMessage", errorMessage);

        // Giữ lại dữ liệu đã nhập để user không phải nhập lại
        request.setAttribute("fullName", request.getParameter("fullName"));
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("phone", request.getParameter("phone"));

        // Quay lại trang đăng ký
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
