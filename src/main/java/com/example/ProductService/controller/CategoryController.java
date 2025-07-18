package com.example.ProductService.controller;

import com.example.ProductService.feign.UserClient;
import com.example.ProductService.model.dto.CatalogResponse;
import com.example.ProductService.model.entity.Category;
import com.example.ProductService.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
@CrossOrigin(origins = "http://localhost:4200")

public class CategoryController {

    private final CategoryService categoryService;
    private final UserClient userClient;

    @GetMapping
    public CatalogResponse<List<Category>> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{id}")
    public CatalogResponse<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public CatalogResponse<Long> addCategory(@RequestHeader("Authorization") String token,
                                            @RequestBody Category category) {

        boolean isAdmin = userClient.checkUserRole(token, "ADMIN");
        if (!isAdmin) {
            return new CatalogResponse<>(false ,"Access denied",0,null);
        }
        return categoryService.addCategory(category);
    }


    @PatchMapping("{id}")
    public CatalogResponse<Long> updateCategory(@RequestHeader("Authorization") String token,
                                               @PathVariable Long id, @RequestBody Category category) {
        boolean isAdmin = userClient.checkUserRole(token, "ADMIN");
        if (!isAdmin) {
            return new CatalogResponse<>(false ,"Access denied",0,null);
        }
        return categoryService.updateCategory(id,category);
    }

    @DeleteMapping("{id}")
    public CatalogResponse<String> deleteCategory(@RequestHeader("Authorization") String token,
                                                 @PathVariable Long id) {
        boolean isAdmin = userClient.checkUserRole(token, "ADMIN");
        if (!isAdmin) {
            return new CatalogResponse<>(false ,"Access denied",0,null);
        }
        return categoryService.deleteCategory(id);
    }

}
