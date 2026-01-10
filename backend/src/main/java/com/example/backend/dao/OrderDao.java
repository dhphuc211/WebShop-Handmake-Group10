package com.example.backend.dao;

import com.example.backend.model.Cart;
import com.example.backend.model.CartItem;
import com.example.backend.model.Order;
import com.example.backend.util.DBConnection;

import java.sql.*;

public class OrderDao {

    // Lưu đơn hàng và chi tiết đơn hàng
    public int saveOrder(Order order, Cart cart) {
        Connection con = null;
        PreparedStatement preOrder = null;
        PreparedStatement preDetail = null;
        ResultSet rs = null;
        int orderId = 0; // Lưu thành công thì trả về orderId nếu không thì 0

        try {
            con = DBConnection.getConnection();
            // Nếu 1 trong 2 thằng lỗi thì rollback không cho thanh toán
            con.setAutoCommit(false);

            // insert vào bảng order
            String sqlOrder = "INSERT INTO orders (user_id, shipping_name, shipping_phone, shipping_address, shipping_fee, note, total_amount, order_status, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preOrder = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            preOrder.setInt(1, order.getUser_id());
            preOrder.setString(2, order.getShipping_name());
            preOrder.setString(3, order.getShipping_phone());
            preOrder.setString(4, order.getShipping_address());
            preOrder.setDouble(5, order.getShipping_fee());
            preOrder.setString(6, order.getNote());
            preOrder.setDouble(7, order.getTotal_amount());
            preOrder.setString(8, "Pending"); // Mặc định trạng thái là chờ xử lý
            preOrder.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

            int affectedRows = preOrder.executeUpdate();

            // Nếu insert thành công thì lấy id ra
            if (affectedRows > 0) {
                rs = preOrder.getGeneratedKeys();
                if (rs.next()) {
                    // Lấy id tự tằng của đơn hàng xong cập nhật vào order
                    orderId = rs.getInt(1);
                    order.setId(orderId);
                }
            } else {
                con.rollback();
                return 0;
            }

            // insert vào bảng detail
            String sqlDetail = "INSERT INTO order_items (order_id, product_id, price, quantity, total_price) VALUES (?, ?, ?, ?, ?)";
            preDetail = con.prepareStatement(sqlDetail);

            // Duyệt giỏ hàng
            for (CartItem item : cart.getItems()) {
                preDetail.setInt(1, orderId); // id của đơn hàng
                preDetail.setInt(2, item.getProduct().getId());
                preDetail.setDouble(3, item.getProduct().getPrice());
                preDetail.setInt(4, item.getQuantity());
                preDetail.setDouble(5, item.getProduct().getPrice() * item.getQuantity());

                preDetail.addBatch();
            }
            preDetail.executeBatch();

            con.commit();

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return 0; // Trả về 0 báo lỗi
        } finally {
            // Đóng kết nối
            try {
                if (rs != null) rs.close();
                if (preOrder != null) preOrder.close();
                if (preDetail != null) preDetail.close();
                if (con != null) {
                    con.setAutoCommit(true); // Bật lại auto commit
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderId;
    }
}