package com.boot.Ecommerce.service.impl;

import com.boot.Ecommerce.entity.Product;
import com.boot.Ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProduct() {
        Product sampleProduct = new Product();
        sampleProduct.setId("product123");
        sampleProduct.setName("Sample Product");
        sampleProduct.setDescription("Description");
        sampleProduct.setPrice(100.0);

        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);
        Product savedProduct = productService.saveProduct(sampleProduct);

        assertEquals(sampleProduct, savedProduct);
    }

    @Test
    public void testGetProductsByIds() {
        List<String> productIds = new ArrayList<>();
        productIds.add("product1");
        productIds.add("product2");

        Product product1 = new Product();
        product1.setId("product1");
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setId("product2");
        product2.setName("Product 2");

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product1);
        expectedProducts.add(product2);

        when(productRepository.findAllById(anyList())).thenReturn(expectedProducts);

        List<Product> retrievedProducts = productService.getProductsByIds(Optional.of(productIds));

        assertEquals(expectedProducts, retrievedProducts);
    }

    @Test
    public void testGetProductById() {
        String productId = "product123";
        Product sampleProduct = new Product();
        sampleProduct.setId(productId);
        sampleProduct.setName("Sample Product");

        when(productRepository.findById(anyString())).thenReturn(Optional.of(sampleProduct));

        Optional<Product> retrievedProduct = productService.getProductById(productId);

        assertTrue(retrievedProduct.isPresent());
        assertEquals(sampleProduct, retrievedProduct.get());
    }

    @Test
    public void testUpdateProduct() {
        String productId = "product123";
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Existing Product");
        existingProduct.setDescription("Description");
        existingProduct.setPrice(50.0);

        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setPrice(75.0);

        when(productRepository.findById(eq(productId))).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProduct(productId, updatedProduct);

        assertEquals(updatedProduct, result);
        assertEquals("Updated Product", result.getName());
        assertEquals("Updated Description", result.getDescription());
        assertEquals(75.0, result.getPrice());
    }

    @Test
    public void testRemoveProduct() {
        List<String> productIds = new ArrayList<>();
        productIds.add("product1");
        productIds.add("product2");

        String successMessage = productService.removeProduct(productIds);

        assertEquals("Product removed successfully", successMessage);
    }

    @Test
    public void testRemoveProductWithInvalidId() {
        String invalidProductId = "invalidId";

        when(productRepository.findById(eq(invalidProductId))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(invalidProductId, new Product()));
    }
}
