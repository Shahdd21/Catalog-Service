package com.example.ProductService.service;

import com.example.ProductService.exception.ResourceNotFoundException;
import com.example.ProductService.model.Mapper;
import com.example.ProductService.model.dto.CatalogRequest;
import com.example.ProductService.model.dto.CatalogResponse;
import com.example.ProductService.model.dto.ProductDTO;
import com.example.ProductService.model.entity.Category;
import com.example.ProductService.model.entity.Product;
import com.example.ProductService.repository.CategoryRepository;
import com.example.ProductService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CatalogResponse<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new CatalogResponse<>(true, "Success", products.size(), products);
    }

    @Override
    public CatalogResponse<Product> getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return new CatalogResponse<>(true, "Success", 1, product);
    }


    @Override
    public CatalogResponse<List<Product>> getProductByCategoryID(Long categoryID) {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryID));

        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("There are no products in this category.");
        }

        return new CatalogResponse<>(true, "Success", products.size(), products);
    }

    @Override
    public CatalogResponse<Long> addProduct(CatalogRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Optional<Product> existingProduct = productRepository.findByNameAndCategory(request.getName(), category);

        if (existingProduct.isPresent()) {
            return new CatalogResponse<>(false, "Product already exists", 0, null);
        }

        Product product = Mapper.toEntity(request, category);
        Product savedProduct = productRepository.save(product);
        return new CatalogResponse<>(true, "Product added successfully", 1, savedProduct.getId());
    }


    @Override
    public CatalogResponse<Long> updateProduct(Long id, CatalogRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Mapper.updateEntity(product, request, category);
        productRepository.save(product);
        return new CatalogResponse<>(true, "Product updated successfully", 1, product.getId());
    }



    @Override
    public CatalogResponse<String> deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        productRepository.delete(existingProduct);
        return new CatalogResponse<>(true, "Product deleted successfully", 0, "Product ID: " + existingProduct.getId());

    }

    @Override
    public ProductDTO getProductDTOForStore(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no product with id " + id));

        return new ProductDTO(product.getId(), product.getName(), product.getDescription(),
                product.getImageCover(), product.getPrice());
    }

    @Override
    public List<ProductDTO> getAllProductsForStore() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for(Product product : products){
            productDTOS.add(new ProductDTO(
                    product.getId(), product.getName(), product.getDescription(),
                    product.getImageCover(), product.getPrice()
            ));
        }

        return productDTOS;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


}
