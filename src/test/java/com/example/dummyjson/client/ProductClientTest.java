package com.example.dummyjson.client;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.response.ProductResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductClientTest {

    @Autowired
    private ProductClient productClient;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void setupWireMock() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        WireMock.configureFor("localhost", 8080);
    }

    @AfterAll
    static void teardownWireMock() {
        wireMockServer.stop();
    }

    @Test
    //Utilizado Wiremock para simular a chamada na api externa.
    public void testGetAllProducts() {
        // Configura uma resposta simulada para o endpoint
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"products\": [ {\"id\": 1, \"description\": \"Product1\"}, {\"id\": 2, \"description\": \"Product2\"} ] }")));

        ProductResponse response = productClient.getAllProducts();

        // Valida o comportamento
        List<Product> products = response.getProducts();
        assertEquals(30, products.size());
        assertEquals("The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.", products.get(0).getDescription());
    }

    @Test
    public void testGetProductById() {
        // Configura uma resposta simulada para o endpoint com ID
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/1"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"description\": \"Product1\"}")));

        // Chama o método real do cliente Feign
        Product product = productClient.getProductById(1L);

        // Valida o comportamento
        assertEquals(1L, product.getId());
        assertEquals("The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.", product.getDescription());
    }
}
