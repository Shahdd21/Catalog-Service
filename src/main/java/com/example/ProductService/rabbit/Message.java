package com.example.ProductService.rabbit;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Message {

    private Long productId;
    private boolean isAvailable;
}
