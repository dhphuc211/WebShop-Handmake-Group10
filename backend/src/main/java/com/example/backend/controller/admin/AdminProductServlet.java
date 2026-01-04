package com.example.backend.controller.admin;

import com.example.backend.model.Product;
import com.example.backend.model.ProductAttribute;
import com.example.backend.model.ProductImage;
import com.example.backend.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "AdminProductServlet", value = "/admin/products")
public class AdminProductServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "insert":
                insertProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    // --- CÁC HÀM HIỂN THỊ (GET) ---

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gọi service lấy danh sách (dùng hàm cho Admin để thấy cả sản phẩm ẩn)
        List<Product> listProducts = productService.getAllProductsForAdmin();
        request.setAttribute("listProducts", listProducts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/products/product-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/products/product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productService.getProduct(id);

        // Lấy thêm thuộc tính nếu có
        // ProductAttribute attr = productService.getAttribute(id);
        // request.setAttribute("attribute", attr);

        request.setAttribute("product", existingProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/products/product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect(request.getContextPath() + "/admin/products?message=deleted");
    }

    // --- CÁC HÀM XỬ LÝ DỮ LIỆU (POST) ---

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 1. Lấy thông tin cơ bản
        String name = request.getParameter("product_name");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String desc = request.getParameter("full_description");
        String status = request.getParameter("status");
        boolean featured = request.getParameter("featured") != null;
        int categoryId = 1; // Hoặc request.getParameter("category_id")

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setStock(stock);
        newProduct.setFullDescription(desc);
        newProduct.setStatus(status);
        newProduct.setFeatured(featured);
        newProduct.setCategoryId(categoryId);

        String imageUrl = request.getParameter("image_url");

        // Gán vào đối tượng để Service xử lý
        if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            ProductImage pImg = new ProductImage();
            pImg.setImageUrl(imageUrl.trim());
            newProduct.setImageUrl(pImg);
        }

        // 3. Gọi Service lưu vào DB
        productService.insertProduct(newProduct);

        response.sendRedirect(request.getContextPath() + "/admin/products?message=inserted");
    }

    // --- XỬ LÝ CẬP NHẬT (POST) ---
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("product_name");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String desc = request.getParameter("full_description");
        String status = request.getParameter("status");
        boolean featured = request.getParameter("featured") != null;

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setFullDescription(desc);
        product.setStatus(status);
        product.setFeatured(featured);

        // LẤY LINK ẢNH MỚI (NẾU CÓ)
        String imageUrl = request.getParameter("image_url");
        if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            ProductImage pImg = new ProductImage();
            pImg.setImageUrl(imageUrl.trim());
            product.setImageUrl(pImg);
        }

        productService.updateProduct(product, null);
        response.sendRedirect(request.getContextPath() + "/admin/products?message=updated");
    }
}