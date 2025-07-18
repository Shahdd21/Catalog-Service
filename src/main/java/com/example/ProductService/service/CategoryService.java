package com.example.ProductService.service;

import com.example.ProductService.model.dto.CatalogRequest;
import com.example.ProductService.model.dto.CatalogResponse;
import com.example.ProductService.model.entity.Category;
import com.example.ProductService.model.entity.Product;

import java.util.List;

public interface CategoryService {
    CatalogResponse<List<Category>> getAllCategories();
    CatalogResponse<Category> getCategoryById(Long id);

    CatalogResponse<Long> addCategory(Category request);
    CatalogResponse<Long> updateCategory(Long id,Category category);
    CatalogResponse<String> deleteCategory(Long id);
}
