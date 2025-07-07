package com.boot.Ecommerce.service;

import com.boot.Ecommerce.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getProductsByIds(Optional<List<String>> ids);
    Optional<Product> getProductById(String id);
    Product updateProduct(String id, Product product);
    String removeProduct(List<String> ids);
}
