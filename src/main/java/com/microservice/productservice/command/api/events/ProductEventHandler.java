package com.microservice.productservice.command.api.events;

import com.microservice.productservice.command.api.data.Product;
import com.microservice.productservice.command.api.data.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventHandler {

    private final ProductRepository productRepository;
    @EventHandler
    public void on (ProductCreatedEvent productCreatedEvent){
        Product product = new Product();
        BeanUtils.copyProperties(productCreatedEvent,product);
          productRepository.save(product);
    }
}
