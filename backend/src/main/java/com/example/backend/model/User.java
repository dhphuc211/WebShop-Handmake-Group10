package com.example.backend.model;

import java.sql.Timestamp;

/**
 * Model User - Đại diện cho một người dùng trong hệ thống
 * Chứa các thông tin cơ bản của user như: id, tên, email, password, phone, role...
 */
public class User {
    private int id;                    // ID tự tăng trong database
    private String fullName;           // Họ và tên đầy đủ
    private String email;              // Email (dùng để đăng nhập)
    private String phone;              // Số điện thoại (cũng có thể dùng để đăng nhập)
    private String password;           // Mật khẩu (đã được mã hóa)
    private String role;               // Vai trò: "user" hoặc "admin"
    private boolean isActive;          // Trạng thái: true = hoạt động, false = bị khóa
    private Timestamp createdAt;       // Thời gian tạo tài khoản
    private Timestamp updatedAt;       // Thời gian cập nhật gần nhất

    //Constructor mặc định - cần thiết cho việc tạo object rỗng
    public User() {
    }

    //Constructor đầy đủ - dùng khi lấy dữ liệu từ database
    public User(int id, String fullName, String email, String phone,
                String password, String role, boolean isActive,
                Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor cho đăng ký - chỉ cần thông tin cơ bản
     */
    public User(String fullName, String email, String phone, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = "user";        // Mặc định là user thường
        this.isActive = true;      // Mặc định là active
    }

    // geter/seter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    //Hiển thị thông tin user
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
