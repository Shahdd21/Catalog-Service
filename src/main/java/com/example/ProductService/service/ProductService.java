package com.example.ProductService.service;

import com.example.ProductService.model.dto.CatalogRequest;
import com.example.ProductService.model.dto.CatalogResponse;
import com.example.ProductService.model.dto.ProductDTO;
import com.example.ProductService.model.entity.Product;

import java.util.List;

public interface ProductService {

    CatalogResponse<List<Product>> getAllProducts();
    CatalogResponse<Product> getProductById(Long id);
    CatalogResponse<List<Product>> getProductByCategoryID(Long categoryID);
    CatalogResponse<Long> addProduct(CatalogRequest request);
    CatalogResponse<Long> updateProduct(Long id , CatalogRequest product);
    CatalogResponse<String> deleteProduct(Long id);

    ProductDTO getProductDTOForStore(Long id);
    List<ProductDTO> getAllProductsForStore();
    Product saveProduct(Product product);
}
