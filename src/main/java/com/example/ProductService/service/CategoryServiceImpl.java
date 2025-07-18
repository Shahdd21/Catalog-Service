package com.example.ProductService.service;

import com.example.ProductService.exception.ResourceNotFoundException;
import com.example.ProductService.model.Mapper;
import com.example.ProductService.model.dto.CatalogResponse;
import com.example.ProductService.model.entity.Category;
import com.example.ProductService.model.entity.Product;
import com.example.ProductService.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public CatalogResponse<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return new CatalogResponse<>(true, "Success", categories.size(), categories);

    }

    @Override
    public CatalogResponse<Category> getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Subcategory is not found for this ID: " + id)
        );
        return new CatalogResponse<>(true, "Success", 1, category);
    }

    @Override
    public CatalogResponse<Long> addCategory(Category request) {
        Optional<Category> existingCategory = categoryRepository.findByName(request.getName());
        if (existingCategory.isPresent()) {
            return new CatalogResponse<>(false, "Category already exists", 0, null);
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setImageUrl(request.getImageUrl());
        category.setProducts(new ArrayList<>());

        Category saved = categoryRepository.save(category);
        return new CatalogResponse<>(true, "Category added successfully", 1, saved.getId());
    }

    @Override
    public CatalogResponse<Long> updateCategory(Long id ,Category request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (request.getName() != null) category.setName(request.getName());
        if (request.getImageUrl() != null) category.setImageUrl(request.getImageUrl());

        categoryRepository.save(category);
        return new CatalogResponse<>(true, "category updated successfully", 1, category.getId());
    }

    @Override
    public CatalogResponse<String> deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found"));
        categoryRepository.delete(existingCategory);
        return new CatalogResponse<>(true, "Category deleted successfully", 0, "Product ID: " + existingCategory.getId());

    }
}
