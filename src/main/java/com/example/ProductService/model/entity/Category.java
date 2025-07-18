package com.example.ProductService.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    private Long parentCategoryId;
    private String imageUrl;

    @OneToMany(cascade =CascadeType.ALL ,mappedBy = "category")
    private List<Product> products;
}
