package com.example.backend.controller.user;

import com.example.backend.dao.BlogDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BlogDAO blogDAO = new BlogDAO();

        // Lấy tin nổi bật cho trang chủ
        request.setAttribute("featuredPosts", blogDAO.getFeaturedPosts(3));

        // Có thể lấy thêm banner, sản phẩm...
        // request.setAttribute("banners", bannerService.getAll());

        // Sau khi có đủ dữ liệu mới mở trang index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
