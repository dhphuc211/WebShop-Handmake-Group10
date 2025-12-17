package com.example.backend.model;

public class ProductAttribute {

    // 1. Khóa chính và Khóa ngoại
    private int id;                 // ID riêng của bảng attribute (nếu có)
    private String productId;       // Khóa ngoại trỏ sang bảng Product (lưu ý: kiểu String để khớp với Product.id)

    // 2. Các thuộc tính chi tiết (Lấy từ , )
    private String origin;          // Xuất xứ (VD: Bát Tràng, Hội An...)
    private String material;        // Chất liệu (VD: Gốm, Tre, Lụa...)
    private String size;            // Kích thước (VD: 20x30cm, L, XL...)
    private double weight;          // Trọng lượng (VD: 0.5 kg - dùng double để có thể tính phí ship sau này)
    private String color;           // Màu sắc (VD: Xanh ngọc, Nâu đất...)

    // =======================================================
    // CONSTRUCTORS
    // =======================================================

    // Constructor rỗng
    public ProductAttribute() {
    }

    // Constructor đầy đủ
    public ProductAttribute(int id, String productId, String origin, String material, String size, double weight, String color) {
        this.id = id;
        this.productId = productId;
        this.origin = origin;
        this.material = material;
        this.size = size;
        this.weight = weight;
        this.color = color;
    }

    // Constructor không cần ID (dùng khi insert mới)
    public ProductAttribute(String productId, String origin, String material, String size, double weight, String color) {
        this.productId = productId;
        this.origin = origin;
        this.material = material;
        this.size = size;
        this.weight = weight;
        this.color = color;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ProductAttribute{" +
                "productId='" + productId + '\'' +
                ", material='" + material + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}