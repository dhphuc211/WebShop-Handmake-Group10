package com.example.backend.controller.admin;

import com.example.backend.dao.CategoryDAO;
import com.example.backend.model.Category;
import com.example.backend.model.Sale;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/categories")
@MultipartConfig
public class AdminCategoryServlet extends HttpServlet { // Sửa từ WebServlet thành HttpServlet
    private CategoryDAO categoryDAO = new CategoryDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String id = request.getParameter("id");

        // TRƯỜNG HỢP: HIỂN THỊ TRANG CHỈNH SỬA
        if ("edit".equals(action) && id != null) {
            Category category = categoryDAO.getCategoryById(id);
            request.setAttribute("category", category);
            request.setAttribute("salesList", categoryDAO.getAllSales()); // Lấy dữ liệu từ bảng sale

            request.getRequestDispatcher("/admin/categories/edit.jsp").forward(request, response);
            return; // Dừng tại đây để không chạy xuống phần hiển thị danh sách
        }

        // TRƯỜNG HỢP: HIỂN THỊ DANH SÁCH MẶC ĐỊNH
        List<Category> allCategories = categoryDAO.getAllCategories();
        List<Category> rootCategories = allCategories.stream()
                .filter(c -> c.getParentId() == 0)
                .collect(Collectors.toList());

        request.setAttribute("categories", rootCategories);
        request.setAttribute("allCategories", allCategories);
        request.setAttribute("categoriesCount", allCategories.size());

        request.getRequestDispatcher("/admin/categories/list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy lựa chọn từ Radio Button (Sử dụng mã cũ hay tạo mới)
        String saleOption = request.getParameter("sale_option");
        int finalSaleId = 0;

        if ("existing".equals(saleOption)) {
            // TRƯỜNG HỢP: Sử dụng mã có sẵn từ dropdown
            String saleIdStr = request.getParameter("sale_id");
            if (saleIdStr != null && !saleIdStr.isEmpty()) {
                finalSaleId = Integer.parseInt(saleIdStr);
            }
        }
        else if ("new".equals(saleOption)) {
            // TRƯỜNG HỢP: Tạo mã mới nhanh
            String newSaleIdStr = request.getParameter("new_sale_id");
            String newSalePercentStr = request.getParameter("new_sale_percent");
            String startInput = request.getParameter("new_sale_start");
            String endInput = request.getParameter("new_sale_end");

            if (newSaleIdStr != null && !newSaleIdStr.trim().isEmpty()) {
                try {
                    int newSaleId = Integer.parseInt(newSaleIdStr.trim());
                    double percent = Double.parseDouble(newSalePercentStr) / 100.0;

                    Sale newSale = new Sale();
                    newSale.setId(newSaleId);
                    newSale.setDiscountPercent(percent);

                    // Chuyển đổi datetime-local sang java.sql.Timestamp cho Database
                    if (startInput != null && !startInput.isEmpty()) {
                        newSale.setStartSale(Timestamp.valueOf(startInput.replace("T", " ") + ":00"));
                    }
                    if (endInput != null && !endInput.isEmpty()) {
                        newSale.setEndSale(Timestamp.valueOf(endInput.replace("T", " ") + ":00"));
                    }

                    // Lưu mã mới vào bảng sale trước
                    categoryDAO.insertSale(newSale);
                    finalSaleId = newSaleId;
                } catch (Exception e) {
                    e.printStackTrace(); // In lỗi nếu nhập ID không phải là số (Database dùng int(11))
                }
            }
        }

        // 2. Thực hiện cập nhật bảng product_categories
        String categoryName = request.getParameter("category_name");
        String catIdStr = request.getParameter("id"); // ID ẩn từ form

        if (catIdStr != null && !catIdStr.isEmpty()) {
            try {
                int categoryId = Integer.parseInt(catIdStr);
                // Gửi dữ liệu xuống DAO để thực thi SQL UPDATE
                categoryDAO.updateCategorySale(categoryId, categoryName, finalSaleId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Quay lại trang danh sách sau khi sửa xong
        response.sendRedirect(request.getContextPath() + "/admin/categories");
    }
}
