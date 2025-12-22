package com.example.backend.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private double salePrice;
    private String image;
    private int stock;
    private String description;
    private int categoryId;

    public Product(int id, String name, double price, double salePrice, String image, int stock, String description, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.image = image;
        this.stock = stock;
        this.description = description;
        this.categoryId = categoryId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Nếu không giảm giá thì sẽ trả về price là giá gôc của chúng ta
    public double getSalePrice() {
        if (salePrice == 0) return price;
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
