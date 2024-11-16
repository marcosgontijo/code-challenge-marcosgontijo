package com.example.dummyjson.client;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productClient", url = "${api.dummyjson.url}")
public interface ProductClient {

    @GetMapping
    ProductResponse getAllProducts();

    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") Long id);

}