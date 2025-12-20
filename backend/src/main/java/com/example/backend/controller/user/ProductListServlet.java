package com.example.backend.controller.user;

import com.example.backend.model.Product;
import com.example.backend.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

// Đường dẫn truy cập: /products (Ví dụ: /products?cid=DM01 hoặc /products?search=ghe)
@WebServlet(name = "ProductListServlet", value = "/products")
public class ProductListServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Thiết lập tiếng Việt
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 2. Lấy tham số
        String categoryIdStr = request.getParameter("cid"); // Lấy chuỗi trước
        String searchKeyword = request.getParameter("search");
        String sortType = request.getParameter("sort");

        List<Product> productList;

        // 3. Phân luồng xử lý
        try {
            if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
                // A. Tìm kiếm
                // Giả sử Service có hàm gọi đến dao.searchProductsByName
                productList = productService.searchProducts(searchKeyword);
                request.setAttribute("title", "Tìm kiếm: " + searchKeyword);

            } else if (categoryIdStr != null && !categoryIdStr.trim().isEmpty()) {
                // B. Lọc theo danh mục (SỬA LỖI Ở ĐÂY)
                try {
                    int cid = Integer.parseInt(categoryIdStr); // Ép kiểu String -> int
                    // Gọi Service -> DAO.getProductsByCategory(cid)
                    productList = productService.getProductsByCategory(cid);
                    request.setAttribute("title", "Sản phẩm theo danh mục");
                } catch (NumberFormatException e) {
                    // Nếu cid không phải số (vd: cid=abc), quay về mặc định
                    productList = productService.getAllProducts();
                }

            } else if (sortType != null && !sortType.trim().isEmpty()) {
                // C. Sắp xếp
                productList = productService.getAllProductsSorted(sortType);
                request.setAttribute("title", "Sắp xếp theo giá");

            } else {
                // D. Mặc định: Lấy tất cả sản phẩm Active
                // Sửa tên hàm cho khớp với DAO (không dùng hàm ForAdmin)
                productList = productService.getAllProducts();
                request.setAttribute("title", "Tất cả sản phẩm");
            }
        } catch (Exception e) {
            e.printStackTrace();
            productList = null; // Tránh lỗi null pointer
        }

        // 4. Gửi dữ liệu sang JSP
        request.setAttribute("productList", productList);

        // Giữ lại trạng thái filter để hiển thị trên giao diện (nếu cần)
        request.setAttribute("paramSearch", searchKeyword);
        request.setAttribute("paramCid", categoryIdStr);

        // 5. Forward sang JSP
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}