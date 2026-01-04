package com.example.backend.model;

import java.sql.Timestamp;

public class Product {

    private int id;
    private String name;
    private ProductImage pimage;

    private double price;
    private int stock;

    private String fullDescription;
    private String status;

    private boolean isFeatured;

    // 5. Khóa ngoại (Foreign Keys)
    private int categoryId;

    // 6. Thời gian (Timestamps)
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private ProductAttribute attribute;

    public Product() {
    }

    public Product(int id, String name, ProductImage pimage, double price, int stock, String fullDescription, String status, boolean isFeatured, int categoryId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.pimage = pimage;
        this.price = price;
        this.stock = stock;
        this.fullDescription = fullDescription;
        this.status = status;
        this.isFeatured = isFeatured;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductImage getPimage() {
        return pimage;
    }

    public void setImageUrl(ProductImage imageUrl) {
        this.pimage = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ProductAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(ProductAttribute attribute) {
        this.attribute = attribute;
    }


    public String getImageUrl() {
        // Nếu pimage có dữ liệu, lấy link ảnh từ đó
        if (this.pimage != null && this.pimage.getImageUrl() != null) {
            return this.pimage.getImageUrl();
        }
        // Nếu không có ảnh, trả về link ảnh mặc định để không bị lỗi giao diện
        return "https://via.placeholder.com/300";
    }

    // toString() để debug in ra console xem dữ liệu
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}