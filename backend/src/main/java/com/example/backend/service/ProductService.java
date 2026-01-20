package com.example.backend.service;

import com.example.backend.dao.ProductDAO;
import com.example.backend.model.Product;
import com.example.backend.model.ProductAttribute;
import com.example.backend.model.ProductImage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();


    public List<Product> getFeaturedProducts() {
        return productDAO.getFeaturedProducts();
    }


    public List<Product> getSaleProducts() {
        return productDAO.getSaleProducts();
    }

    public Product getProduct(int id) {
        return productDAO.getProductById(id);
    }


    public boolean updateProduct(Product product, ProductAttribute attribute) {
        boolean isProductUpdated = productDAO.updateProduct(product);

        if (isProductUpdated && attribute != null) {
            // return attributeDAO.updateAttribute(attribute);
            return true; // Tạm thời trả về true
        }
        return isProductUpdated;
    }

    public int calculateDiscountPercentage(double originalPrice, double salePrice) {
        if (originalPrice == 0) return 0;
        double percent = ((originalPrice - salePrice) / originalPrice) * 100;
        return (int) Math.round(percent);
    }

    public List<Product> getAllProducts(int offset, int pageSize) {
        return productDAO.getAllProducts(offset, pageSize);
    }

    public int getTotalPages(int pageSize) {
        int totalRecords = productDAO.getTotalProductsCount();
        return (int) Math.ceil((double) totalRecords/pageSize);
    }

    public List<Product> searchProducts(String keyword) {
        return productDAO.searchProductsByName(keyword);
    }

    public List<Product> getProductsByCategory(int cid) {
        return productDAO.getProductsByCategory(cid);
    }

    public List<Product> getAllProductsSorted(String sortType, String direction) {
        return productDAO.getAllProductsSorted(sortType, direction);
    }

    public Product getFullProductDetail(int id) {
        Product product = productDAO.getProductById(id);

        if (product != null) {
            ProductAttribute attr = productDAO.getAttributeByProductId(id);
            product.setAttribute(attr);
        }
        return product;
    }

    public List<Product> getRelatedProducts(int categoryId, int currentProductId) {
        return productDAO.getProductsByCategory(categoryId);
    }

    public void insertProduct(Product p) {
        int newId = productDAO.insertProduct(p);

        if (newId > 0 && p.getImageUrl() != null && !p.getImageUrl().isEmpty()) {
            productDAO.insertProductImage(newId, p.getImageUrl());
        }
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }


}