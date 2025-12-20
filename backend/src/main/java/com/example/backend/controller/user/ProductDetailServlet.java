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

@WebServlet(name = "ProductDetailServlet", value = "/product-detail")
public class ProductDetailServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. Lấy tham số dưới dạng String trước
        String idStr = request.getParameter("id");
        int pid;

        // 2. Validate: Kiểm tra Null hoặc Rỗng trước khi xử lý
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        // 3. Cố gắng chuyển String sang int (Xử lý trường hợp id=abc hoặc id=12x)
        try {
            pid = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            // Nếu id không phải số -> Đá về trang danh sách
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        // 4. Gọi Service với ID đã parse thành công (pid)
        Product product = productService.getFullProductDetail(pid);

        // 5. Nếu không tìm thấy sản phẩm trong DB
        if (product == null) {
            // Có thể redirect về trang 404 hoặc trang chủ
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        // 6. Lấy sản phẩm liên quan
        List<Product> relatedProducts = productService.getRelatedProducts(product.getCategoryId(), pid);

        // 7. Đẩy dữ liệu sang JSP
        request.setAttribute("product", product);
        request.setAttribute("relatedProducts", relatedProducts);

        request.getRequestDispatcher("/product-detail.jsp").forward(request, response);
    }
}