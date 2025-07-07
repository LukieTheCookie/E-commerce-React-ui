package com.boot.Ecommerce.service.impl;

import com.boot.Ecommerce.entity.Product;
import com.boot.Ecommerce.repository.ProductRepository;
import com.boot.Ecommerce.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsByIds(Optional<List<String>> ids) {
        List<Product> products;
        if (ids.isPresent() && !ids.get().isEmpty()) {
            products = productRepository.findAllById(ids.get());
        } else {
            products = productRepository.findAll();
        }
        return products;
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(String id, Product product) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        if (Objects.nonNull(product.getName())) {
            existingProduct.setName(product.getName());
        }
        if (Objects.nonNull(product.getDescription())) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != 0.0) {
            existingProduct.setPrice(product.getPrice());
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public String removeProduct(List<String> ids) {
        for (String id: ids){
            productRepository.deleteById(id);
        }
        return "Product removed successfully";
    }
}
