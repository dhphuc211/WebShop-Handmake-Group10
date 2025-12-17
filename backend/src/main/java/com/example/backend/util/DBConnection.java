package com.example.backend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

    // 1. Cấu hình Database (Bạn sửa lại cho đúng máy bạn nhé)
    // Nếu dùng MySQL:
    private static final String URL = "jdbc:mysql://localhost:3306/webshop_db";
    private static final String USER = "root";       // Tên đăng nhập (thường là root)
    private static final String PASS = "123123";     // Mật khẩu database

    // 2. Hàm lấy kết nối
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Đăng ký driver (bắt buộc với các version cũ, mới thì tự nhận)
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Kết nối Database thành công!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Kết nối thất bại: " + e.getMessage());
        }
        return conn;
    }

    // 3. Hàm chạy câu lệnh Update (dùng cho các câu SQL bạn vừa viết)
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

    // 4. Main test thử luôn tại đây
    public static void main(String[] args) {
        // Thử chạy câu lệnh update số 1
        String sql = "SELECT * from product_categories";

        runUpdate(sql);
    }
}