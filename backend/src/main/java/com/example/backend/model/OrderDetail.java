package com.example.backend.model;

import java.sql.Timestamp;

public class OrderDetail {
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;
    private double total_price;
    private Timestamp created_at;
    private Timestamp updated_at;

    public OrderDetail() {
    }

    public OrderDetail(int id, int order_id, int product_id, int quantity, double total_price, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.total_price = total_price;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", total_price=" + total_price +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
