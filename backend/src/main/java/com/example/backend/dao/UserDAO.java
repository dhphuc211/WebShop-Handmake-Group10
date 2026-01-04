package com.example.backend.dao;

import com.example.backend.model.User;
import com.example.backend.util.DBConnection;
import java.sql.*;

public class UserDAO {
    /**
     * Đăng ký tài khoản mới
     *
     * @param user Đối tượng User chứa thông tin đăng ký
     * @return true nếu đăng ký thành công, false nếu thất bại
     */
    public boolean register(User user) {
        String sql = "INSERT INTO users (full_name, email, phone, password, role, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getRole());
            pstmt.setBoolean(6, user.isActive());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi đăng ký user: " + e.getMessage());
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    /**
     * Kiểm tra đăng nhập
     * @param emailOrPhone Email hoặc số điện thoại
     * @param password Mật khẩu
     * @return User object nếu đăng nhập đúng, null nếu sai
     */
    public User checkLogin(String emailOrPhone, String password) {
        String sql = "SELECT * FROM users WHERE (email = ? OR phone = ?) AND password = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // Set tham số
            pstmt.setString(1, emailOrPhone);
            pstmt.setString(2, emailOrPhone);
            pstmt.setString(3, password);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đăng nhập: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return null;
    }

    /**
     * Kiểm tra email đã tồn tại chưa
     */
    public boolean isEmailExists(String email) {
        return findByEmail(email) != null;
    }

    private User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
            return null;

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm user theo email: " + e.getMessage());
            return null;
        } finally {
            closeResources(conn, pstmt, rs);
        }
    }

    /**
     * Kiểm tra phone đã tồn tại chưa
     */
    public boolean isPhoneExists(String phone) {
        return findByPhone(phone) != null;
    }

    private User findByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phone = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
            return null;

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm user theo phone: " + e.getMessage());
            return null;
        } finally {
            closeResources(conn, pstmt, rs);
        }
    }

    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        // Đóng ResultSet trước
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng ResultSet: " + e.getMessage());
            }
        }

        // Đóng Statement
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng Statement: " + e.getMessage());
            }
        }

        // Đóng Connection cuối cùng
//        DBConnection.closeConnection(conn);
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        // Mapping tạm thời, cần kiểm tra lại tên cột trong database
        try {
            // Giả định tên cột, nếu khác bạn cần sửa lại cho đúng với DB
            if (hasColumn(rs, "id")) user.setId(rs.getInt("id"));
            if (hasColumn(rs, "full_name")) user.setFullName(rs.getString("full_name"));
            if (hasColumn(rs, "email")) user.setEmail(rs.getString("email"));
            if (hasColumn(rs, "phone")) user.setPhone(rs.getString("phone"));
            if (hasColumn(rs, "password")) user.setPassword(rs.getString("password"));
            if (hasColumn(rs, "role")) user.setRole(rs.getString("role"));
            if (hasColumn(rs, "is_active")) user.setActive(rs.getBoolean("is_active"));
        } catch (SQLException e) {
             System.err.println("Lỗi mapping user: " + e.getMessage());
        }
        return user;
    }

    private boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnLabel(x))) {
                return true;
            }
        }
        return false;
    }
}
