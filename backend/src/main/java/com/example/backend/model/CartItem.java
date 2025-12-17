package com.example.backend.model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product,int quantity){
        this.product = product;
        this.quantity = quantity;
    }
    // Tính tổng tiền của sản phẩm
    // Hàm getSalePrice bên product sẽ thực hiện việc
    // Nếu giá giảm thì lấy salePrice không giảm thì lấy giá gốc để tính tổng tiền
    public double getTotalPrice() {
        return product.getSalePrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
