package com.example.ProductService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long productId;
    private String name;
    private String description;
    private String imageCover;
    private Double price;
}
