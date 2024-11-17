package com.example.dummyjson.service;

import com.example.dummyjson.client.ProductClient;
import com.example.dummyjson.dto.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Value("${api.dummyjson.url}")
    private String apiUrl;

    private final ProductClient productClient;

    public ProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public List<Product> getAllProducts() {
        return productClient.getAllProducts().getProducts();
    }

    public Product getProductById(Long id) {
        String url = apiUrl + "/" + id;
        return productClient.getProductById(id);
    }
}
