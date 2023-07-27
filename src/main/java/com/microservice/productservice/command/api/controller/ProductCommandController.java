package com.microservice.productservice.command.api.controller;

import com.microservice.productservice.command.api.command.CreateProductCommand;
import com.microservice.productservice.command.api.model.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {

    private final  CommandGateway commandGateway;
    @PostMapping
    public  String addProduct(@RequestBody ProductRestModel productRestModel){
        CreateProductCommand command = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .name(productRestModel.getName())
                .price(productRestModel.getPrice())
                .quantity(productRestModel.getQuantity())
                .build();
      String result =  commandGateway.sendAndWait(command);
          return result;
    }
}
