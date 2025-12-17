package com.example.backend.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Integer,CartItem> items;

    public Cart() {
        items = new HashMap<>();
    }

    // Thêm sản phẩm vào giỏ
    public void add(Product product, int quantity) {
        if (product == null) return;

        int id = product.getId();

        if (items.containsKey(id)) {
            CartItem cartItem = items.get(id);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            items.put(id, new CartItem(product, quantity));
        }
    }

    // Cập nhật số lượng
    public void update(int productId, int quantity) {
        if (items.containsKey(productId)) {
            if (quantity <= 0) {
                items.remove(productId);
            } else {
                items.get(productId).setQuantity(quantity);
            }
        }
    }

    // Xóa sản phẩm khỏi giỏ
    public void remove(int productId) {
        items.remove(productId);
    }

    // Tính tổng tiền cả giỏ hàng
    public double getTotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.getTotalPrice();
        }
        return total;
    }

    // Đếm tổng số lượng sản phẩm hiển thị trên phần header (icon)
    public int getTotalQuantity() {
        int count = 0;
        for (CartItem item : items.values()) {
            count += item.getQuantity();
        }
        return count;
    }

    // Lấy danh sách items
    public Map<Integer, CartItem> getItems() {
        return items;
    }

    // Xóa sạch giỏ hàng
    public void clear() {
        items.clear();
    }
}
