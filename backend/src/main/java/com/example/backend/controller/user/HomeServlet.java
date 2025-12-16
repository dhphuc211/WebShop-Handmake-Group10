package com.example.backend.controller.user;

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

        // Ví dụ dữ liệu giả (sau này lấy từ DB)
        List<Map<String, Object>> categories = new ArrayList<>();

        categories.add(Map.of("name", "Mây tre đan", "total", 0));
        categories.add(Map.of("name", "Gốm sứ", "total", 30));
        categories.add(Map.of("name", "Đồ gỗ mỹ nghệ", "total", 0));

        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/views/index.jsp")
                .forward(request, response);
    }
}
