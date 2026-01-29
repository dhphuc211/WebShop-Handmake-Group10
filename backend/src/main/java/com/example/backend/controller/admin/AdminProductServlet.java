package com.example.backend.controller.admin;

import com.example.backend.model.Product;
import com.example.backend.model.ProductAttribute;
import com.example.backend.model.ProductImage;
import com.example.backend.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProductServlet", value = "/admin/products")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
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

    private String getFinalImageUrl(HttpServletRequest request) throws IOException, ServletException {
        // Ưu tiên lấy file upload trước
        Part filePart = request.getPart("image_file");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
            // Lưu vào thư mục 'uploads' trong webapp
            String uploadPath = getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            filePart.write(uploadPath + File.separator + fileName);
            return "uploads/" + fileName;
        }
        // Nếu không có file thì lấy từ ô input URL
        return request.getParameter("image_url");
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startParam = request.getParameter("start");
        String lengthParam = request.getParameter("length");

        int offset = (startParam != null) ? Integer.parseInt(startParam) : 0;
        int limit = (lengthParam != null) ? Integer.parseInt(lengthParam) : 1000;

        // GỌI HÀM NÀY: Để offset truyền thẳng vào DAO không qua tính toán (page-1)
        List<Product> listProducts = productService.getAllProductsForAdmin(offset, limit);

        request.setAttribute("listProducts", listProducts);
        request.getRequestDispatcher("/admin/products/product-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/products/product-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productService.getFullProductDetail(id);
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
        // 1. Map thông tin cơ bản
        Product newProduct = mapRequestToProduct(request);

        // 2. Tạo đối tượng Attribute để chứa thông tin chi tiết
        ProductAttribute pa = new ProductAttribute();
        pa.setMaterial(request.getParameter("material"));
        pa.setOrigin(request.getParameter("origin"));
        pa.setSize(request.getParameter("size"));
        pa.setWeight(request.getParameter("weight"));
        pa.setColor(request.getParameter("color"));

        // 3. Xử lý ảnh
        String imageUrl = getFinalImageUrl(request);
        ProductImage pImg = new ProductImage();
        pImg.setImageUrl(imageUrl);
        newProduct.setImage(pImg);

        // 4. GỌI SERVICE: Bạn cần sửa hàm này trong ProductService để nhận cả ProductAttribute
        productService.insertFullProduct(newProduct, pa);

        response.sendRedirect(request.getContextPath() + "/admin/products?message=inserted");
    }
    // --- XỬ LÝ CẬP NHẬT (POST) ---
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 1. Lấy thông tin cơ bản
        Product product = mapRequestToProduct(request);
        int productId = Integer.parseInt(request.getParameter("id"));
        product.setId(productId);

        // 2. Lấy thông tin thuộc tính từ Form
        ProductAttribute pa = new ProductAttribute();
        pa.setProductId(productId); // Đừng quên set ID để biết update cho sp nào
        pa.setMaterial(request.getParameter("material"));
        pa.setOrigin(request.getParameter("origin"));
        pa.setSize(request.getParameter("size"));
        pa.setWeight(request.getParameter("weight"));
        pa.setColor(request.getParameter("color"));

        // 3. Gọi service cập nhật cả hai
        productService.updateProduct(product, pa);

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