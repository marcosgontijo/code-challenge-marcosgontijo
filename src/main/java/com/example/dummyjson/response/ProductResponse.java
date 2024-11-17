package com.example.dummyjson.response;

import com.example.dummyjson.dto.Product;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private List<Product> products;

    public ProductResponse() {
    }

    public ProductResponse(List<Product> products) {
        this.products = products;
    }
}