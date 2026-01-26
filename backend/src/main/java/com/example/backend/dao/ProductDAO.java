package com.example.backend.dao;

import com.example.backend.model.Product;
import com.example.backend.model.ProductAttribute;
import com.example.backend.model.ProductImage;
import com.example.backend.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductDAO {

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
    public List<Product> getAllProducts(int offset, int limit) {
        List<Product> list = new ArrayList<>();

//        String sql = "select p.*, pi.image_url from products p "
//                + "left join product_images pi on p.id = pi.product_id "+
//                "group by p.id order by p.id desc limit ?,?";
        String sql = "SELECT p.*, pi.image_url FROM products p "
                + "INNER JOIN product_images pi ON p.id = pi.product_id "
                + "WHERE pi.image_url IS NOT NULL AND pi.image_url != '' "
                + "GROUP BY p.id ORDER BY p.id LIMIT ?, ?";

        try (Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalProductsCount() {
        String sql = "select count(*) from products where status = 'active'";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
            if (rs.next()) { return rs.getInt(1); }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.*, MAX(pi.image_url) AS image_url " +
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

    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT p.*, MAX(pi.image_url) AS image_url " +
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

    public List<Product> getAllProductsSorted(String orderBy, String direction) {
        List<Product> list = new ArrayList<>();

        Set<String> allowedOrderBy = Set.of(
                "price", "name", "created_at", "stock"
        );
        if (!allowedOrderBy.contains(orderBy)) {
            orderBy = "price";
        }
        String sortDir = "ASC";
        if ("DESC".equalsIgnoreCase(direction)) {
            sortDir = "DESC";
        }

        String sql = "SELECT * FROM products WHERE status = 'active' ORDER BY "+ orderBy+ " " + sortDir;

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


    public boolean updateProduct(Product p) {
        String sql = "UPDATE products SET name=?, price=?, stock=?, " +
                "full_description=?, status=?, is_featured=?, " +
                "category_id=?, updated_at=NOW() WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());

            ps.setDouble(2, p.getPrice());

            ps.setInt(3, p.getStock());
            ps.setString(4, p.getFullDescription());
            ps.setString(5, p.getStatus());
            ps.setBoolean(6, p.isFeatured());
            ps.setInt(7, p.getCategoryId());
            ps.setInt(8, p.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int insertProduct(Product p) {
        String sql = "INSERT INTO products (name, price, stock, category_id, status, created_at) " +
                "VALUES (?, ?, ?, ?, ?, 'active', NOW())";

        int generatedId = -1;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getCategoryId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
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

        p.setImage(img);
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
                attr = new ProductAttribute();
                attr.setOrigin(rs.getString("origin"));
                attr.setMaterial(rs.getString("material"));
                attr.setSize(rs.getString("size"));
                attr.setWeight(rs.getString("weight"));
                attr.setColor(rs.getString("color"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attr;
    }

    public void deleteProduct(int id) {
        String sql = "update products set status = 'inactive' where id = ? ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}