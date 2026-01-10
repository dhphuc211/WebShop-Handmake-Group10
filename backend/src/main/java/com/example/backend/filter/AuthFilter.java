package com.example.backend.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AuthFilter - Bộ lọc bảo vệ trang Admin
 * Chỉ cho phép Admin truy cập vào các link bắt đầu bằng /admin/
 */
@WebFilter("/admin/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Lấy đường dẫn hiện tại
        String path = req.getRequestURI();

        // DANH SÁCH NGOẠI LỆ (Cho phép vào không cần login)
        // Nếu đang vào trang login của admin hoặc file css/js -> Cho qua
        if (path.endsWith("admin-login.jsp") || 
            path.contains("/css/") || 
            path.contains("/js/")) {
            
            chain.doFilter(request, response);
            return;
        }

        // KIỂM TRA ĐĂNG NHẬP VÀ QUYỀN
        HttpSession session = req.getSession(false);
        
        // Kiểm tra: Có session + Có user + Role là admin
        if (session != null && 
            session.getAttribute("user") != null && 
            "admin".equals(session.getAttribute("userRole"))) {
            
            // Thỏa mãn -> Cho phép đi tiếp
            chain.doFilter(request, response);
            return;
        }

        // NẾU KHÔNG HỢP LỆ -> CHUYỂN VỀ TRANG LOGIN ADMIN
        System.out.println("Chặn truy cập trái phép: " + path);
        res.sendRedirect(req.getContextPath() + "/admin/admin-login.jsp");
    }

    @Override
    public void destroy() {
        // Hủy filter
    }
}
