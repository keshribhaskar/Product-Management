package com.assignment.ekart.productms.service;

import com.assignment.ekart.productms.model.ProductDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ObjectMapper mapper;
    ProductDetails product = new ProductDetails();
    @BeforeAll
    public void setup(){
        product.setName("Shampoo");
        product.setDescription("hair shampoo");
        product.setCategory("shampoo");
        product.setBrand("Clinic plus");
        product.setPrice(1.0);
        product.setAvailableQuantity(320);
        productService.addProduct(product);
    }

    @Test
    @Order(1)
    public void getAllProductsTest() throws JsonProcessingException {
        String expected = "[{\"productId\":1,\"name\":\"Shampoo\",\"description\":\"hair shampoo\",\"category\":\"shampoo\",\"brand\":\"Clinic plus\",\"price\":1.0,\"availableQuantity\":320}]";
        List<ProductDetails> productDetails = productService.getAllProducts();
        String actual = mapper.writeValueAsString(productDetails);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    @Order(2)
    public void addProductTest() throws JsonProcessingException {
        String expected = "Product added.";
        String actual = productService.addProduct(product);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    @Order(3)
    public void getProductByIdTest() throws Exception {
        int productId = 1;
        String expected = "{\"productId\":1,\"name\":\"Shampoo\",\"description\":\"hair shampoo\",\"category\":\"shampoo\",\"brand\":\"Clinic plus\",\"price\":1.0,\"availableQuantity\":320}";
        ProductDetails productDetails = productService.getProductById(productId);
        String actual = mapper.writeValueAsString(productDetails);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @Order(4)
    public void addProductFailedTest() {
        String expected = "Product addition failed. Cannot invoke \"com.assignment.ekart.productms.model.ProductDetails.getName()\" because \"product\" is null";
        ProductDetails product1 = new ProductDetails();
        product1 = null;
        String actual = productService.addProduct(product1);
        Assertions.assertEquals(expected,actual);
    }

}
