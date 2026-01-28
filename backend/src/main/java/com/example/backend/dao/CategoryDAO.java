package com.example.backend.dao;

import com.example.backend.model.Category;
import com.example.backend.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        String sql = "select * from product_categories";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getString("id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));
                    category.setImageUrl(rs.getString("image_url"));
                    category.setStatus(rs.getString("status"));
                    category.setSale_id(rs.getInt("sale_id"));
                    category.setCreatedAt(rs.getTimestamp("created_at"));
                    category.setParentId(rs.getInt("parent_id"));
                    if (category.getParentId() == 0) { categories.add(category);}
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
