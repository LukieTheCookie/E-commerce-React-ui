package com.boot.Ecommerce.controller;

import com.boot.Ecommerce.entity.Product;
import com.boot.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product product1 = productService.saveProduct(product);
        return ResponseEntity.ok(product1);
    }

    @PostMapping("/remove-products")
    public ResponseEntity<Void> deleteProduct(@RequestBody Map<String, List<String>> request){
        List<String> ids = request.get("ids");
        if (ids != null && !ids.isEmpty()) {
            productService.removeProduct(ids);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(name = "ids", required = false) List<String> ids){
        Optional<List<String>> idList = Optional.ofNullable(ids);
        return ResponseEntity.ok(productService.getProductsByIds(idList));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable String id){
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/product/{productid}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "productid") String productId, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }
}
