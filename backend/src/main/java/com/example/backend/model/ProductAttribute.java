package com.example.backend.model;

public class ProductAttribute {

    private int id;
    private String productId;

    private String origin;
    private String material;
    private String size;
    private double weight;
    private String color;


    public ProductAttribute() {
    }

    public ProductAttribute(int id, String productId, String origin, String material, String size, double weight, String color) {
        this.id = id;
        this.productId = productId;
        this.origin = origin;
        this.material = material;
        this.size = size;
        this.weight = weight;
        this.color = color;
    }

    public ProductAttribute(String productId, String origin, String material, String size, double weight, String color) {
        this.productId = productId;
        this.origin = origin;
        this.material = material;
        this.size = size;
        this.weight = weight;
        this.color = color;
    }


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