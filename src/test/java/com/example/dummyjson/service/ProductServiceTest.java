package com.example.dummyjson.service;

import com.example.dummyjson.client.ProductClient;
import com.example.dummyjson.dto.Product;
import com.example.dummyjson.response.ProductResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductClient productClient;

    @Value("${api.dummyjson.url}")
    private String apiUrl;

    //ARRUMAR NULLPOINT
    @Test
    public void testGetAllProducts() {
        // Dados simulados
        List<Product> mockProducts = List.of(new Product(1L, "Product1"), new Product(2L, "Product2"));
        ProductResponse mockResponse = new ProductResponse(mockProducts);

        // Configuração do mock
        when(productClient.getAllProducts()).thenReturn(mockResponse);

        // Teste do serviço
        List<Product> products = productService.getAllProducts();

        // Asserções
        assertEquals(2, products.size());
        assertEquals("Product1", products.get(0).getDescription());
    }

    //ARRUMAR NULLPOINT
    @Test
    public void testGetProductById() {
        // Mockando a resposta do cliente
        Product mockProduct = new Product(1L, "Product1");
        when(productClient.getProductById(1L)).thenReturn(mockProduct);

        // Executando o método de serviço
        Product product = productService.getProductById(1L);

        // Verificando o resultado
        Assertions.assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Product1", product.getDescription());
    }
}
