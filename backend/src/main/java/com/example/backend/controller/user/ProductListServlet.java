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

@WebServlet(name = "ProductListServlet", value = "/products")
public class ProductListServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String categoryIdStr = request.getParameter("cid"); // Lấy chuỗi trước
        String searchKeyword = request.getParameter("search");
        String sortType = request.getParameter("sort");

        List<Product> productList;

        try {
            if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
                // Tìm kiếm
                productList = productService.searchProducts(searchKeyword);
                request.setAttribute("title", "Tìm kiếm: " + searchKeyword);

            } else if (categoryIdStr != null && !categoryIdStr.trim().isEmpty()) {
                // Lọc theo danh mục
                try {
                    int cid = Integer.parseInt(categoryIdStr);
                    productList = productService.getProductsByCategory(cid);
                    request.setAttribute("title", "Sản phẩm theo danh mục");
                } catch (NumberFormatException e) {
                    productList = productService.getAllProducts();
                }

            } else if (sortType != null && !sortType.trim().isEmpty()) {
                // Sắp xếp
                productList = productService.getAllProductsSorted(sortType);
                request.setAttribute("title", "Sắp xếp theo giá");

            } else {
                // Mặc định: Lấy tất cả sản phẩm
                productList = productService.getAllProducts();
                request.setAttribute("title", "Tất cả sản phẩm");
            }
        } catch (Exception e) {
            e.printStackTrace();
            productList = null;
        }

        request.setAttribute("productList", productList);

        request.setAttribute("paramSearch", searchKeyword);
        request.setAttribute("paramCid", categoryIdStr);

        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}