package com.microservice.productservice.query.api;

import com.microservice.productservice.command.api.data.Product;
import com.microservice.productservice.command.api.data.ProductRepository;
import com.microservice.productservice.command.api.model.ProductRestModel;
import com.microservice.productservice.query.api.query.GetProductQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductProjection {

    private  final ProductRepository productRepository;
    @QueryHandler
    public List<ProductRestModel> handle(GetProductQuery getProductsQuery) {
        List<Product> products =
                productRepository.findAll();

        List<ProductRestModel> productRestModels =
                products.stream()
                        .map(product -> ProductRestModel
                                .builder()
                                .quantity(product.getQuantity())
                                .price(product.getPrice())
                                .name(product.getName())
                                .build())
                        .collect(Collectors.toList());

        return productRestModels;
    }
}
