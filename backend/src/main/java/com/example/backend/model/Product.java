package com.example.backend.model;

import java.sql.Timestamp;

public class Product {
    // 1. Các thuộc tính cơ bản (Basic Info)
    private String id;              // Ánh xạ ID dạng chuỗi như 'P01'
    private String name;            // Tên sản phẩm [cite: 21]
    private String slug;            // Đường dẫn thân thiện SEO [cite: 399]
    private String imageUrl;        // Link ảnh đại diện [cite: 22]

    // 2. Giá và Kho (Price & Stock)
    private double price;           // Giá gốc [cite: 22]
    private double salePrice;       // Giá đã giảm (nếu có lưu cứng trong DB) [cite: 22]
    private int stock;              // Số lượng tồn kho [cite: 16]

    // 3. Mô tả và Trạng thái (Description & Status)
    private String fullDescription; // Mô tả chi tiết [cite: 240]
    private String status;          // Trạng thái: 'active', 'inactive' [cite: 223]

    // 4. Các cờ đánh dấu (Flags - boolean)
    // Trong DB thường lưu là bit(1) hoặc tinyint(1), trong Java dùng boolean cho dễ xử lý logic
    private boolean isFeatured;     // Sản phẩm nổi bật [cite: 24]
    private boolean isNew;          // Sản phẩm mới [cite: 425]
    private boolean isBestseller;   // Sản phẩm bán chạy [cite: 426]

    // 5. Khóa ngoại (Foreign Keys)
    private String categoryId;      // Mã danh mục (dạng 'DM01') [cite: 251]
    private int saleId;             // Mã đợt giảm giá (nếu có) [cite: 39]

    // 6. Thời gian (Timestamps)
    private Timestamp createdAt;    // Ngày tạo [cite: 400]
    private Timestamp updatedAt;    // Ngày cập nhật [cite: 428]

    // =======================================================
    // CONSTRUCTORS
    // =======================================================

    // Constructor rỗng (Bắt buộc phải có để khởi tạo object ban đầu)
    public Product() {
    }

    // Constructor đầy đủ tham số
    public Product(String id, String name, String slug, String imageUrl, double price, double salePrice, int stock, String fullDescription, String status, boolean isFeatured, boolean isNew, boolean isBestseller, String categoryId, int saleId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.imageUrl = imageUrl;
        this.price = price;
        this.salePrice = salePrice;
        this.stock = stock;
        this.fullDescription = fullDescription;
        this.status = status;
        this.isFeatured = isFeatured;
        this.isNew = isNew;
        this.isBestseller = isBestseller;
        this.categoryId = categoryId;
        this.saleId = saleId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // =======================================================
    // GETTERS & SETTERS
    // =======================================================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
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

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isBestseller() {
        return isBestseller;
    }

    public void setBestseller(boolean bestseller) {
        isBestseller = bestseller;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
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