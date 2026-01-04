package com.example.backend.service;

import com.example.backend.dao.CategoryDAO;
import com.example.backend.model.Category;

import java.util.List;

public class CategoriesService {
    private CategoryDAO categoryDAO;
    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }
}
