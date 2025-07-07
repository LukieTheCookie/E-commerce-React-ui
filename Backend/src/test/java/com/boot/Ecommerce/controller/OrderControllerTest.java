package com.boot.Ecommerce.controller;

import com.boot.Ecommerce.entity.Order;
import com.boot.Ecommerce.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import java.util.Collections;

@SpringBootTest
public class OrderControllerTest {
    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        Order sampleOrder = new Order();
        sampleOrder.setId("order123");
        sampleOrder.setCustomerId("customer123");
        sampleOrder.setTotal(100);
        sampleOrder.setProducts(Arrays.asList("product1", "product2", "product3"));

        when(orderService.createOrder(any(Order.class))).thenReturn(sampleOrder);
        ResponseEntity<Order> response = orderController.createOrder(sampleOrder);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(sampleOrder, response.getBody());
    }

    @Test
    public void testFetchOrders() {

        Map<String, Object> invalidRequest = new HashMap<>();
        invalidRequest.put("customerId", "customer123");
        when(orderService.fetchOrders(null, "customer123")).thenReturn(Collections.emptyList());

        ResponseEntity<List<Order>> response = orderController.fetchOrders(invalidRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

}
