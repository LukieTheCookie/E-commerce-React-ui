package com.boot.Ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "VARCHAR(32)")
    private String id;

    @Column(name = "paid")
    private boolean paid;

    @JsonProperty("customer_id")
    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "products")
    private List<String> products;

    @Column(name = "total")
    private float total;

    public Order() {
    }

    public Order(boolean paid, String customerId, List<String> products, float total) {
        this.paid = paid;
        this.customerId = customerId;
        this.products = products;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
