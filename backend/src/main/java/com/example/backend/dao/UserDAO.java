package com.example.backend.dao;

import com.example.backend.model.User;
import com.example.backend.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    /**
     * Đăng ký tài khoản mới
     *
     * @param user Đối tượng User chứa thông tin đăng ký
     * @return true nếu đăng ký thành công, false nếu thất bại
     */
    public boolean register(User user) {
        String sql = "INSERT INTO users (full_name, email, phone, password, role_id, status, created_at) " +
                "VALUES (?, ?, ?, ?, COALESCE((SELECT id FROM role WHERE name = ?), 1), ?, NOW())";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getPassword());
            String status = user.isActive() ? "active" : "inactive";
            pstmt.setString(5, user.getRole());
            pstmt.setString(6, status);
            
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
        String sql = "SELECT u.*, r.name AS role " +
                "FROM users u " +
                "LEFT JOIN role r ON u.role_id = r.id " +
                "WHERE (u.email = ? OR u.phone = ?) AND u.password = ?";
        
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

    //Tìm user theo email hoặc số điện thoại (dùng cho quên mật khẩu)
    public User findByEmailOrPhone(String emailOrPhone) {
        String sql = "SELECT u.*, r.name AS role " +
                "FROM users u " +
                "LEFT JOIN role r ON u.role_id = r.id " +
                "WHERE u.email = ? OR u.phone = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emailOrPhone);
            pstmt.setString(2, emailOrPhone);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm user theo email/phone: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return null;
    }

    //Cập nhật mật khẩu theo user id
    public boolean updatePassword(int userId, String hashedPassword) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, hashedPassword);
            pstmt.setInt(2, userId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật mật khẩu: " + e.getMessage());
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    /**
     * Kiểm tra email đã tồn tại chưa
     */
    public boolean isEmailExists(String email) {
        return findByEmail(email) != null;
    }

    private User findByEmail(String email) {
        String sql = "SELECT u.*, r.name AS role " +
                "FROM users u " +
                "LEFT JOIN role r ON u.role_id = r.id " +
                "WHERE u.email = ?";

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
        String sql = "SELECT u.*, r.name AS role " +
                "FROM users u " +
                "LEFT JOIN role r ON u.role_id = r.id " +
                "WHERE u.phone = ?";

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

    //Lấy thông tin user theo ID
    public User getUserById(int id) {
        String sql = "SELECT u.*, r.name AS role " +
                "FROM users u " +
                "LEFT JOIN role r ON u.role_id = r.id " +
                "WHERE u.id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy user theo id: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return null;
    }

    //Cập nhật thông tin cá nhân (Họ tên, SĐT)
    public boolean updateProfile(User user) {
        String sql = "UPDATE users SET full_name = ?, phone = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getPhone());
            pstmt.setInt(3, user.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật profile: " + e.getMessage());
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    //Lấy danh sách user (có phân trang, tìm kiếm)
    public List<User> getUsers(String keyword, int offset, int limit) {
        List<User> users = new ArrayList<>();
        String trimmedKeyword = keyword == null ? "" : keyword.trim();
        boolean hasKeyword = !trimmedKeyword.isEmpty();
        int safeOffset = Math.max(0, offset);
        int safeLimit = Math.max(1, limit);

        StringBuilder sql = new StringBuilder("SELECT u.*, r.name AS role ");
        sql.append("FROM users u ");
        sql.append("LEFT JOIN role r ON u.role_id = r.id ");
        sql.append("WHERE (r.name IS NULL OR r.name <> 'admin') ");
        if (hasKeyword) {
            sql.append("AND (u.full_name LIKE ? OR u.email LIKE ? OR u.phone LIKE ?) ");
        }
        sql.append("ORDER BY u.created_at DESC ");
        sql.append("LIMIT ? OFFSET ?");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql.toString());

            int index = 1;
            if (hasKeyword) {
                String pattern = "%" + trimmedKeyword + "%";
                pstmt.setString(index++, pattern);
                pstmt.setString(index++, pattern);
                pstmt.setString(index++, pattern);
            }
            pstmt.setInt(index++, safeLimit);
            pstmt.setInt(index, safeOffset);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách user: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return users;
    }

    //Đếm tổng user (phục vụ phân trang)
    public int countUsers(String keyword) {
        String trimmedKeyword = keyword == null ? "" : keyword.trim();
        boolean hasKeyword = !trimmedKeyword.isEmpty();

        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM users u ");
        sql.append("LEFT JOIN role r ON u.role_id = r.id ");
        sql.append("WHERE (r.name IS NULL OR r.name <> 'admin') ");
        if (hasKeyword) {
            sql.append("AND (u.full_name LIKE ? OR u.email LIKE ? OR u.phone LIKE ?)");
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql.toString());

            if (hasKeyword) {
                String pattern = "%" + trimmedKeyword + "%";
                pstmt.setString(1, pattern);
                pstmt.setString(2, pattern);
                pstmt.setString(3, pattern);
            }

            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đếm user: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return 0;
    }

    //Cập nhật trạng thái user (khóa/mở khóa). Không áp dụng cho admin.
    public boolean updateUserStatus(int userId, boolean isActive) {
        String status = isActive ? "active" : "inactive";
        String sql = "UPDATE users u " +
                "LEFT JOIN role r ON u.role_id = r.id " +
                "SET u.status = ? " +
                "WHERE u.id = ? AND (r.name IS NULL OR r.name <> 'admin')";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, userId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật trạng thái user: " + e.getMessage());
            return false;
        } finally {
            closeResources(conn, pstmt, null);
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
            if (hasColumn(rs, "role_id")) user.setRoleId(rs.getInt("role_id"));
            if (hasColumn(rs, "role")) user.setRole(rs.getString("role"));
            if (hasColumn(rs, "created_at")) user.setCreatedAt(rs.getTimestamp("created_at"));
            if (hasColumn(rs, "updated_at")) user.setUpdatedAt(rs.getTimestamp("updated_at"));
            if (hasColumn(rs, "is_active")) {
                user.setActive(rs.getBoolean("is_active"));
            } else if (hasColumn(rs, "status")) {
                String status = rs.getString("status");
                user.setActive("active".equalsIgnoreCase(status));
            }
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
