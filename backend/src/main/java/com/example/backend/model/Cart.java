package com.example.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Cart implements Serializable {
    Map<Integer,CartItem> items;
    private User user;

    public Cart() {
        items = new HashMap<>();
    }

    // Thêm sản phẩm vào giỏ
    public void add(Product product, int quantity) {
        if(quantity <= 0){
            quantity = 1;
        }

        if(get(product.getId())!=null){
            items.get(product.getId()).updateQuantity(quantity);
        }

        else{
            items.put(product.getId(),new CartItem(product,quantity,product.getPrice()));
        }
    }

    // Cập nhật số lượng
    public boolean update(int productId, int quantity) {
        if(get(productId)==null){
            return false;
        }

        if(quantity<=0){
            items.remove(productId);
            return true;
        }

        items.get(productId).setQuantity(quantity);
        return true;

    }

    // Xóa sản phẩm khỏi giỏ
    public CartItem remove(int productId) {
        if(get(productId)==null){
            return null;
        }

        return items.remove(productId);
    }

    // Xóa tất cả sản phẩm
    public List<CartItem> removeAll(){
        List<CartItem> list = new ArrayList<>(items.values());
        items.clear();
        return list;
    }

    // Tính tổng tiền cả giỏ hàng
    public double getTotalMoney() {
        AtomicReference<Double> total = new AtomicReference<>(0.0);

        getItems().forEach(item->{
            total.updateAndGet(v -> v+(item.getQuantity()*item.getPrice()));
        });
        return total.get();
    }

    // Đếm tổng số lượng sản phẩm hiển thị trên phần header (icon)
    public int getTotalQuantity() {
        AtomicInteger total = new AtomicInteger(0);

        getItems().forEach(item->{
            total.addAndGet(item.getQuantity());
        });
        return total.get();

    }

    // Lấy danh sách items
    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    private CartItem get(int id){
        return items.get(id);
    }

    // Thông tin khách hàng
    public void updateCustomerInfo(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
