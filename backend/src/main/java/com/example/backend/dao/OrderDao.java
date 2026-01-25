package com.example.backend.dao;

import com.example.backend.model.*;
import com.example.backend.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            String sqlDetail = "INSERT INTO order_items (order_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)";
            preDetail = con.prepareStatement(sqlDetail);

            // Duyệt giỏ hàng
            for (CartItem item : cart.getItems()) {
                preDetail.setInt(1, orderId); // id của đơn hàng
                preDetail.setInt(2, item.getProduct().getId());
                preDetail.setInt(3, item.getQuantity());
                preDetail.setDouble(4, item.getProduct().getPrice() * item.getQuantity());

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
    // Lấy danh sách đơn hàng trong quản lí đơn hàng admin
    public List<Order> getAllOrders(){
        List<Order> list = new ArrayList<>();
        String sql = "select * from orders order by id desc";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery()){

            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setUser_id(rs.getInt("user_id"));
                o.setShipping_name(rs.getString("shipping_name"));
                o.setShipping_phone(rs.getString("shipping_phone"));
                o.setShipping_address(rs.getString("shipping_address"));
                o.setNote(rs.getString("note"));
                o.setShipping_fee(rs.getDouble("shipping_fee"));
                o.setTotal_amount(rs.getDouble("total_amount"));
                o.setOrder_status(rs.getString("order_status"));
                o.setCreated_at(rs.getTimestamp("created_at"));
                o.setUpdated_at(rs.getTimestamp("updated_at"));

                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // Xem đơn hàng theo id
    public Order getOrderById(int id){
        String sql = "select * from orders where id = ?";
        try(Connection con  = DBConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)){

            pre.setInt(1,id);
            ResultSet rs= pre.executeQuery();

            if(rs.next()){
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setUser_id(rs.getInt("user_id"));
                o.setShipping_name(rs.getString("shipping_name"));
                o.setShipping_phone(rs.getString("shipping_phone"));
                o.setShipping_address(rs.getString("shipping_address"));
                o.setNote(rs.getString("note"));
                o.setShipping_fee(rs.getDouble("shipping_fee"));
                o.setTotal_amount(rs.getDouble("total_amount"));
                o.setOrder_status(rs.getString("order_status"));
                o.setCreated_at(rs.getTimestamp("created_at"));
                return o;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // Lấy danh sách sản phẩm trong đơn hàng
    public List<OrderItem> getOrderItems(int orderId) {
        List<OrderItem> list = new ArrayList<>();

        String sql = "select oi.*, p.name, p.price, " +
                "(select image_url from product_images where product_id = p.id limit 1) as image_url " +
                "from order_items oi " +
                "join products p on oi.product_id = p.id " +
                "where oi.order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setTotalPrice(rs.getDouble("total_price"));

                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));

                String url = rs.getString("image_url");
                ProductImage pi = new ProductImage();
                pi.setProductId(p.getId());
                pi.setImageUrl(url); // Gán link ảnh vào đây

                p.setImage(pi);

                item.setProduct(p);
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}