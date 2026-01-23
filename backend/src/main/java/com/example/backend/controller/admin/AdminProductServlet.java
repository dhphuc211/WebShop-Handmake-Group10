package com.example.backend.controller.admin;

import com.example.backend.model.Product;
import com.example.backend.model.ProductImage;
import com.example.backend.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        String startParam = request.getParameter("start");
        String lengthParam = request.getParameter("length");

        int offset = (startParam != null) ? Integer.parseInt(startParam) : 0;
        int limit = (lengthParam != null) ? Integer.parseInt(lengthParam) : 1000;

        List<Product> listProducts = productService.getAllProducts(offset, limit);

        request.setAttribute("listProducts", listProducts);
        request.getRequestDispatcher("/admin/products/product-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/products/product-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productService.getProduct(id);
        request.setAttribute("product", existingProduct);
        request.getRequestDispatcher("/admin/products/product-form.jsp").forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect(request.getContextPath() + "/admin/products?message=deleted");
    }

    // --- CÁC HÀM XỬ LÝ DỮ LIỆU (POST) ---

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Product newProduct = mapRequestToProduct(request);
        productService.insertProduct(newProduct);
        response.sendRedirect(request.getContextPath() + "/admin/products?message=inserted");    }

    // --- XỬ LÝ CẬP NHẬT (POST) ---
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Product product = mapRequestToProduct(request);
        product.setId(Integer.parseInt(request.getParameter("id")));
        productService.updateProduct(product, null);
        response.sendRedirect(request.getContextPath() + "/admin/products?message=updated");
    }

    private Product mapRequestToProduct(HttpServletRequest request) {
        Product product = new Product();
        product.setName(request.getParameter("name"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setStock(Integer.parseInt(request.getParameter("stock")));
        product.setFullDescription(request.getParameter("full_description"));
        product.setStatus(request.getParameter("status"));
        product.setFeatured(request.getParameter("featured") != null);

        String catId = request.getParameter("category_id");
        if (catId != null) product.setCategoryId(Integer.parseInt(catId));

        String imageUrl = request.getParameter("image_url");
        if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            ProductImage pImg = new ProductImage();
            pImg.setImageUrl(imageUrl.trim());
            product.setImage(pImg);
        }
        return product;
    }
}