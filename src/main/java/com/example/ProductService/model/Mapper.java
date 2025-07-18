package com.example.ProductService.model;

import com.example.ProductService.model.dto.CatalogRequest;
import com.example.ProductService.model.entity.Category;
import com.example.ProductService.model.entity.Product;


import java.util.Date;


//@Mapper(componentModel = "spring")
public interface Mapper {

//    Product toProduct(ProductRequest productRequest);
//    void updateProductFromRequest(ProductRequest request, @MappingTarget Product product);

        public static Product toEntity(CatalogRequest request, Category category) {
            Product product = new Product();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setImageCover(request.getImageCover());
            product.setImages(request.getImages());
            product.setPrice(request.getPrice());
            product.setRatingsAverage(request.getRatingsAverage());
            product.setCategory(category);
            return product;
        }

        public static void updateEntity(Product product, CatalogRequest request, Category category) {
            if (request.getName() != null) product.setName(request.getName());
            if (request.getDescription() != null) product.setDescription(request.getDescription());
            if (request.getImageCover() != null) product.setImageCover(request.getImageCover());
            if (request.getImages() != null) product.setImages(request.getImages());
            if (request.getPrice() != null) product.setPrice(request.getPrice());
            if (request.getRatingsAverage() != null) product.setRatingsAverage(request.getRatingsAverage());
            if (category != null) product.setCategory(category);
        }
    }
