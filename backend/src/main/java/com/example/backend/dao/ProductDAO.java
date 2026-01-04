package com.example.backend.dao;

import com.example.backend.model.Product;
import com.example.backend.model.ProductAttribute;
import com.example.backend.model.ProductImage;
import com.example.backend.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<ProductImage> getImagesByProductId(int pid) {
        List<ProductImage> listImages = new ArrayList<>();

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

        String sql = "SELECT p.*, pi.image_url " +
                "FROM products p " +
                "LEFT JOIN product_images pi ON p.id = pi.product_id " +
                "GROUP BY p.id " +
                "ORDER BY p.id DESC";

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
        String sql = "SELECT p.*, MAX(pi.image_url) AS thumbnail " +
                "FROM products p " +
                "LEFT JOIN product_images pi ON p.id = pi.product_id " +
                "WHERE p.category_id = ? AND p.status = 'active' " +
                "GROUP BY p.id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    /**
     * Xem chi tiết một sản phẩm theo ID
     */
    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT p.*, MAX(pi.image_url) AS thumbnail " +
                "FROM products p " +
                "LEFT JOIN product_images pi ON p.id = pi.product_id " +
                "WHERE p.id = ? " +
                "GROUP BY p.id";

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
    public int insertProduct(Product p) {
        String sql = "INSERT INTO products (name, slug, price, stock, category_id, status, created_at) " +
                "VALUES (?, ?, ?, ?, ?, 'active', NOW())";

        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             // Quan trọng: Thêm tham số RETURN_GENERATED_KEYS
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getName());

            String slug = p.getName().toLowerCase().replaceAll("\\s+", "-");
            ps.setString(2, slug);

            ps.setDouble(3, p.getPrice()); // Index là 3 (sau slug)
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getCategoryId());

            int rows = ps.executeUpdate();

            // Lấy ID vừa sinh ra
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1); // Trả về ID mới nhất
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public void insertProductImage(int productId, String imageUrl) {
        String sql = "INSERT INTO product_images (product_id, image_url) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.setString(2, imageUrl);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        p.setCategoryId(rs.getInt("category_id"));

        ProductImage img = new ProductImage();

        try {
            String url = rs.getString("image_url");
            img.setImageUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        p.setImageUrl(img);
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

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}