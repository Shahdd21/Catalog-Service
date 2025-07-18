package com.example.ProductService.exception;

import com.example.ProductService.model.dto.CatalogResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CatalogResponse<?>> handleResourceNotFoundException(ResourceNotFoundException exc) {
        CatalogResponse<?> productResponse = new CatalogResponse<>(
                false,
                exc.getMessage(),
                0,
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CatalogResponse<?>> handleException(Exception exc) {
        CatalogResponse<?> productResponse = new CatalogResponse<>(
                false,
                "Internal server error: " + exc.getMessage(),
                0,
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productResponse);
    }
}
