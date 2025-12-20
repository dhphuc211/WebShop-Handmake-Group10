package com.example.backend.model;

import java.sql.Timestamp;

public class Product {
    // 1. Các thuộc tính cơ bản (Basic Info)
    private int id;              // Ánh xạ ID dạng chuỗi như 'P01'
    private String name;            // Tên sản phẩm [cite: 21]
    private ProductImage pimage;      // Link ảnh đại diện [cite: 22]

    // 2. Giá và Kho (Price & Stock)
    private double price;           // Giá gốc [cite: 22]
    private int stock;              // Số lượng tồn kho [cite: 16]

    // 3. Mô tả và Trạng thái (Description & Status)
    private String fullDescription; // Mô tả chi tiết [cite: 240]
    private String status;          // Trạng thái: 'active', 'inactive' [cite: 223]

    // 4. Các cờ đánh dấu (Flags - boolean)
    // Trong DB thường lưu là bit(1) hoặc tinyint(1), trong Java dùng boolean cho dễ xử lý logic
    private boolean isFeatured;     // Sản phẩm nổi bật [cite: 24]

    // 5. Khóa ngoại (Foreign Keys)
    private int categoryId;      // Mã danh mục (dạng 'DM01') [cite: 251]     // Mã đợt giảm giá (nếu có) [cite: 39]

    // 6. Thời gian (Timestamps)
    private Timestamp createdAt;    // Ngày tạo [cite: 400]
    private Timestamp updatedAt;    // Ngày cập nhật [cite: 428]

    // Thêm thông số kỹ thuật
    private ProductAttribute attribute;

    // =======================================================
    // CONSTRUCTORS
    // =======================================================

    // Constructor rỗng (Bắt buộc phải có để khởi tạo object ban đầu)
    public Product() {
    }

    // Constructor đầy đủ tham số
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

    // =======================================================
    // GETTERS & SETTERS
    // =======================================================

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