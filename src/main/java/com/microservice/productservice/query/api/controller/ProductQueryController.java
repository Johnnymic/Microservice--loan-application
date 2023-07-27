package com.microservice.productservice.query.api.controller;

import com.microservice.productservice.command.api.model.ProductRestModel;
import com.microservice.productservice.query.api.query.GetProductQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductQueryController {

    private  final QueryGateway queryGateway;

    @GetMapping
    public List<ProductRestModel> getAllProduct(){

        GetProductQuery getProductQuery = new GetProductQuery();

        List<ProductRestModel>  productRestModels= queryGateway.query(getProductQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();
        return productRestModels;
    }
}
