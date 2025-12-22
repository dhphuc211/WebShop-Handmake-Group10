package com.example.backend.model;

import java.sql.Timestamp;

public class Category {

    // 1. Thuộc tính cơ bản
    private String id;              // Mã danh mục (Ví dụ 'DM01' như trong )
    private String name;            // Tên danh mục (Ví dụ 'Điện thoại' )
    private String slug;            // Tên trên URL (Ví dụ 'dien-thoai' )
    private String imageUrl;        // Ảnh đại diện danh mục [cite: 16, 525]

    // 2. Phân cấp (Hierarchy)
    private String parentId;        // ID danh mục cha (Để làm menu đa cấp [cite: 55])

    // 3. Trạng thái & Quản lý
    private String status;          // Trạng thái (Ví dụ 'còn hàng', 'active' )
    private Timestamp createdAt;    // Ngày tạo
    private Timestamp updatedAt;    // Ngày cập nhật

    // =======================================================
    // CONSTRUCTORS
    // =======================================================

    // Constructor rỗng (Bắt buộc)
    public Category() {
    }

    // Constructor đầy đủ
    public Category(String id, String name, String slug, String imageUrl, String parentId, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.imageUrl = imageUrl;
        this.parentId = parentId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Constructor dùng khi thêm mới (Insert) - Không cần ID nếu tự tăng, hoặc có ID nếu nhập tay
    public Category(String name, String slug, String imageUrl, String parentId, String status) {
        this.name = name;
        this.slug = slug;
        this.imageUrl = imageUrl;
        this.parentId = parentId;
        this.status = status;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}