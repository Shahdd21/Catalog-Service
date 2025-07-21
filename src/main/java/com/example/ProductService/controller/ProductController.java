package com.example.ProductService.controller;

import com.example.ProductService.feign.UserClient;
import com.example.ProductService.model.dto.CatalogRequest;
import com.example.ProductService.model.dto.CatalogResponse;
import com.example.ProductService.model.dto.ProductDTO;
import com.example.ProductService.model.entity.Product;
import com.example.ProductService.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;
    private final UserClient userClient;

    @GetMapping
    public CatalogResponse<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/stores")
    public ProductDTO getProductDTOForStore(@RequestParam(name = "product") Long id) {
        return productService.getProductDTOForStore(id);
    }

    @GetMapping("/stores")
    public List<ProductDTO> getAllProductsForStore(){
        return productService.getAllProductsForStore();
    }

    @GetMapping("id/{id}")
    public CatalogResponse<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("category/{category}")
    public CatalogResponse<List<Product>> getProductByCategory(@PathVariable Long category) {
        return productService.getProductByCategoryID(category);
    }


    @PostMapping
    public CatalogResponse<Long> addProduct(@RequestHeader("Authorization") String token,
                                            @RequestBody CatalogRequest product) {

        boolean isAdmin = userClient.checkUserRole(token, "ADMIN");
        if (!isAdmin) {
            return new CatalogResponse<>(false ,"Access denied",0,null);
        }
        return productService.addProduct(product);
    }


    @PatchMapping("{id}")
    public CatalogResponse<Long> updateProduct(@RequestHeader("Authorization") String token,
            @PathVariable Long id, @RequestBody CatalogRequest product) {
        boolean isAdmin = userClient.checkUserRole(token, "ADMIN");
        if (!isAdmin) {
            return new CatalogResponse<>(false ,"Access denied",0,null);
        }
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("{id}")
    public CatalogResponse<String> deleteProduct(@RequestHeader("Authorization") String token,
                                                 @PathVariable Long id) {
        boolean isAdmin = userClient.checkUserRole(token, "ADMIN");
        if (!isAdmin) {
            return new CatalogResponse<>(false ,"Access denied",0,null);
        }
        return productService.deleteProduct(id);
    }
}