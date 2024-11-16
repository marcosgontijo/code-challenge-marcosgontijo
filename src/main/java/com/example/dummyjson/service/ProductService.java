package com.example.dummyjson.service;

import com.example.dummyjson.client.ProductClient;
import com.example.dummyjson.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private Environment env;

    private final ProductClient productClient;

    public ProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public List<Product> getAllProducts() {
        return productClient.getAllProducts().getProducts();
    }

    public Product getProductById(Long id) {
        String url = env.getProperty("BASE_URL") + "/" + id;
        return productClient.getProductById(id);
    }
}
