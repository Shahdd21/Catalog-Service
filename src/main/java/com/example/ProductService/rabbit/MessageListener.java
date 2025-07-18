package com.example.ProductService.rabbit;

import com.example.ProductService.model.entity.Product;
import com.example.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    private final ProductService productService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveMessage(Message message){
        System.out.println("message received: " + message);

        Product product = productService.getProductById(message.getProductId()).getData();
        product.setAvailable(message.isAvailable());

        productService.saveProduct(product);
    }
}
