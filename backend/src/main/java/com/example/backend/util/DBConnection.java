package com.example.backend.util;

import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/webshop_db";
    private static final String USER = "root";
    private static final String PASS = "123123";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Kết nối Database thành công!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Kết nối thất bại: " + e.getMessage());
        }
        return conn;
    }

    public static void runUpdate(String sql) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn != null) {
                int rowsAffected = stmt.executeUpdate();
                System.out.println("Đã cập nhật: " + rowsAffected + " dòng.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void runQuery(String sql) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("--- Kết quả Query ---");
            while (rs.next()) {
                System.out.println("Sản phẩm: " + rs.getString("image_url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sql = "SELECT * FROM banners WHERE status = 'active' " +
                "AND ((start_date IS NULL AND end_date IS NULL) " +
                "OR (DATE_FORMAT(NOW(), '%m-%d') BETWEEN DATE_FORMAT(start_date, '%m-%d') AND DATE_FORMAT(end_date, '%m-%d')))";
        runQuery(sql);
    }

    public static void closeConnection(Connection conn) {
    }
}