package com.example.backend.dao;

import com.example.backend.model.Product;
import com.example.backend.model.ProductAttribute;
import com.example.backend.model.ProductImage;
import com.example.backend.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<ProductImage> getImagesByProductId(int pid) {
        List<ProductImage> listImages = new ArrayList<>();

        // Nên lấy cả ID để sau này có thể xóa/sửa ảnh cụ thể
        String sql = "SELECT id, image_url FROM product_images WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pid);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    ProductImage img = new ProductImage();
                    img.setId(rs.getInt("id"));
                    img.setImageUrl(rs.getString("image_url"));
                    img.setProductId(pid);

                    listImages.add(img);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listImages;
    }

    public List<Product> getFeaturedProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE is_featured = 1 AND status = 'active' LIMIT 8";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * Lấy danh sách sản phẩm đang khuyến mãi
     */
    public List<Product> getSaleProducts() {
        List<Product> list = new ArrayList<>();

        String sql = "SELECT p.* FROM products p JOIN sale s ON p.sale_id = s.id " +
                "WHERE NOW() BETWEEN s.start_sale AND s.end_sale AND p.status = 'active'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Xem danh sách toàn bộ sản phẩm
     */
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();

        String sql = "SELECT p.*, " +
                "(SELECT image_url FROM product_images WHERE product_id = p.id LIMIT 1) AS thumbnail " +
                "FROM products p";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Lọc sản phẩm theo danh mục
     */
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category_id = ? AND status = 'active'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Xem chi tiết một sản phẩm theo ID
     */
    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = mapResultSetToProduct(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * Tìm kiếm sản phẩm theo tên
     */
    public List<Product> searchProductsByName(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ? AND status = 'active'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Sắp xếp sản phẩm theo giá (Tăng/Giảm)
     */
    public List<Product> getAllProductsSorted(String sortType) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE status = 'active' ORDER BY price " +
                (sortType.equalsIgnoreCase("ASC") ? "ASC" : "DESC");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * Cập nhật thông tin sản phẩm
     */
    public boolean updateProduct(Product p) {
        String sql = "UPDATE products SET name=?, slug=?, price=?, sale_price=?, stock=?, " +
                "full_description=?, status=?, is_featured=?, is_new=?, is_bestseller=?, " +
                "category_id=?, updated_at=NOW() WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());

            ps.setDouble(3, p.getPrice());

            ps.setInt(5, p.getStock());
            ps.setString(6, p.getFullDescription());
            ps.setString(7, p.getStatus());
            ps.setBoolean(8, p.isFeatured());
            ps.setInt(11, p.getCategoryId());
            ps.setInt(12, p.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Thêm mới sản phẩm (Dành cho Admin)
     */
    public boolean insertProduct(Product p) {
        String sql = "INSERT INTO products (id, name, slug, price, stock, category_id, status, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'active', NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());
            ps.setDouble(4, p.getPrice());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getCategoryId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Product mapResultSetToProduct(ResultSet rs) throws Exception {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getDouble("price"));
        p.setStock(rs.getInt("stock"));
        p.setFullDescription(rs.getString("full_description"));
        p.setStatus(rs.getString("status"));
        p.setFeatured(rs.getBoolean("is_featured"));
        p.setCategoryId(rs.getInt("category_id")); //

        try {
            String thumb = rs.getString("thumbnail");
            if (thumb != null) {
//                p.setImageUrl(thumb);
            } else {
                // Nếu bảng ảnh chưa có, dùng tạm ảnh mặc định hoặc ảnh cũ
//                p.setImageUrl(rs.getString("image_url"));
            }
        } catch (Exception e) {
            try {
//                p.setImageUrl(rs.getString("image_url"));
            } catch (Exception ex) {
                e.printStackTrace();
            }
        }

        return p;
    }

    public ProductAttribute getAttributeByProductId(int productId) {
        ProductAttribute attr = null;
        String sql = "SELECT * FROM product_attributes WHERE product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Giả sử constructor ProductAttribute(origin, material, size, weight, color)
                attr = new ProductAttribute();
                attr.setOrigin(rs.getString("origin"));
                attr.setMaterial(rs.getString("material"));
                attr.setSize(rs.getString("size"));
                attr.setWeight(rs.getDouble("weight"));
                attr.setColor(rs.getString("color"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attr;
    }
}