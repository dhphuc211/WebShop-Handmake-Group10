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
                System.out.println("Sản phẩm: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sql = "SELECT * from products";

        runQuery(sql);
    }
}