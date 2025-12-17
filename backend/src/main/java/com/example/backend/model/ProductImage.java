package com.example.backend.model;

public class ProductImage {

    // 1. Thuộc tính
    private int id;                 // ID tự tăng của ảnh
    private String productId;       // Khóa ngoại liên kết với bảng Product (String để khớp với Product.id)
    private String imageUrl;        // Đường dẫn file ảnh (Ví dụ: "assets/img/products/binh-gom-1.jpg")

    // =======================================================
    // CONSTRUCTORS
    // =======================================================

    // Constructor rỗng (Bắt buộc)
    public ProductImage() {
    }

    // Constructor đầy đủ
    public ProductImage(int id, String productId, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.imageUrl = imageUrl;
    }

    // Constructor dùng khi thêm mới (không cần ID)
    public ProductImage(String productId, String imageUrl) {
        this.productId = productId;
        this.imageUrl = imageUrl;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}