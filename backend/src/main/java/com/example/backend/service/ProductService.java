package com.example.backend.service;

import com.example.backend.dao.ProductDAO;
import com.example.backend.model.Product;
import com.example.backend.model.ProductAttribute;
import com.example.backend.model.ProductImage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();



    /**
     * Lấy danh sách sản phẩm nổi bật để hiển thị trang chủ
     */
    public List<Product> getFeaturedProducts() {
        return productDAO.getFeaturedProducts();
    }

    /**
     * Lấy danh sách sản phẩm đang khuyến mãi
     */
    public List<Product> getSaleProducts() {
        return productDAO.getSaleProducts();
    }

    /**
     * Lấy chi tiết sản phẩm theo ID
     */
    public Product getProduct(int id) {
        Product product = productDAO.getProductById(id);

        // List<ProductImage> images = imageDAO.getImagesByProductId(id);
        // ProductAttribute attribute = attributeDAO.getAttributeByProductId(id);
        // product.setImages(images); ... (cần sửa Model Product để chứa list này)

        return product;
    }



    /**
     * Sắp xếp sản phẩm theo giá
     */
    public List<Product> getProductsSortedByPrice(String sortType) {
        if ("asc".equalsIgnoreCase(sortType)) {
            return productDAO.getAllProductsSorted("ASC");
        } else {
            return productDAO.getAllProductsSorted("DESC");
        }
    }


    /**
     * Lấy tất cả sản phẩm để hiển thị trong trang Admin
     */
    public List<Product> getAllProductsForAdmin() {
        return productDAO.getAllProducts();
    }

    /**
     * Cập nhật thông tin sản phẩm
     */
    public boolean updateProduct(Product product, ProductAttribute attribute) {
        boolean isProductUpdated = productDAO.updateProduct(product);

        if (isProductUpdated && attribute != null) {
            // return attributeDAO.updateAttribute(attribute);
            return true; // Tạm thời trả về true
        }
        return isProductUpdated;
    }

    /**
     * Tính phần trăm giảm giá để hiển thị lên thẻ sản phẩm
     */
    public int calculateDiscountPercentage(double originalPrice, double salePrice) {
        if (originalPrice == 0) return 0;
        double percent = ((originalPrice - salePrice) / originalPrice) * 100;
        return (int) Math.round(percent);
    }


    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    // Tìm kiếm sản phẩm (Mapping từ searchProducts -> searchProductsByName của DAO)
    public List<Product> searchProducts(String keyword) {
        return productDAO.searchProductsByName(keyword);
    }

    // Lấy sản phẩm theo danh mục (Nhận vào int cid)
    public List<Product> getProductsByCategory(int cid) {
        return productDAO.getProductsByCategory(cid);
    }

    // Sắp xếp sản phẩm (Sửa lỗi dòng 55)
    public List<Product> getAllProductsSorted(String sortType) {
        return productDAO.getAllProductsSorted(sortType);
    }

    // Lấy chi tiết sản phẩm (Dành cho trang Product Detail)
    public Product getFullProductDetail(int id) {
        return productDAO.getProductById(id);
    }

    // Lấy sản phẩm liên quan (Dành cho trang Product Detail)
    public List<Product> getRelatedProducts(int categoryId, int currentProductId) {
        // getRelatedProducts
        // tạm thời dùng getProductsByCategory để thay thế:
        return productDAO.getProductsByCategory(categoryId);
    }
}