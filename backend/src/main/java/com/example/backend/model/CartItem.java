package com.example.backend.model;

public class CartItem {
    private Product product;
    private int quantity;
    private double price;

    public CartItem(Product product,int quantity, double price){
        this.product = product;
        this.quantity = quantity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void updateQuantity(int quantity){
        this.quantity+=quantity;
    }

}
