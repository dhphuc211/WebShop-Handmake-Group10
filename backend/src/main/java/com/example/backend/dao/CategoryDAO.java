package com.example.backend.dao;

import com.example.backend.model.Category;
import com.example.backend.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        String sql = "select * from categories";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getString("id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));
                    category.setImageUrl(rs.getString("imageUrl"));
                    category.setStatus(rs.getString("status"));
                    category.setSale_id(rs.getInt("sale_id"));
                    category.setCreatedAt(rs.getTimestamp("createdAt"));
                    category.setParentId(rs.getInt("parent_id"));

                    categories.add(category);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
