package com.boot.Ecommerce.controller;

import com.boot.Ecommerce.entity.Product;
import com.boot.Ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProduct() {
        Product sampleProduct = new Product();
        sampleProduct.setId("product123");
        sampleProduct.setName("Sample Product");

        when(productService.saveProduct(any(Product.class))).thenReturn(sampleProduct);

        ResponseEntity<Product> response = productController.saveProduct(sampleProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleProduct, response.getBody());
    }

    @Test
    public void testDeleteProduct() {
        List<String> productIds = new ArrayList<>();
        productIds.add("product1");
        productIds.add("product2");

        Map<String, List<String>> request = new HashMap<>();
        request.put("ids", productIds);

        ResponseEntity<Void> response = productController.deleteProduct(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteProductWithEmptyIds() {
        Map<String, List<String>> request = new HashMap<>();
        request.put("ids", new ArrayList<>());

        ResponseEntity<Void> response = productController.deleteProduct(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetAllProducts() {
        List<String> productIds = new ArrayList<>();
        productIds.add("product1");
        productIds.add("product2");

        List<Product> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Product());
        sampleProducts.add(new Product());

        when(productService.getProductsByIds(any(Optional.class))).thenReturn(sampleProducts);

        ResponseEntity<List<Product>> response = productController.getAllProducts(productIds);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleProducts, response.getBody());
    }

    @Test
    public void testGetProductByID() {
        String productId = "product123";
        Product sampleProduct = new Product();
        sampleProduct.setId(productId);
        sampleProduct.setName("Sample Product");

        when(productService.getProductById(anyString())).thenReturn(Optional.of(sampleProduct));

        ResponseEntity<Product> response = productController.getProductByID(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleProduct, response.getBody());
    }

    @Test
    public void testUpdateProduct() {
        String productId = "product123";
        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName("Updated Product");

        when(productService.updateProduct(anyString(), any(Product.class))).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }
}
