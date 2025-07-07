package com.boot.Ecommerce.service.impl;

import com.boot.Ecommerce.entity.Order;
import com.boot.Ecommerce.repository.OrderRepository;
import com.boot.Ecommerce.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setId("1");
        order.setCustomerId("customer123");
        order.setPaid(false);

        Mockito.when(orderRepository.save(ArgumentMatchers.any())).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        Assertions.assertNotNull(createdOrder);
        Assertions.assertEquals("1", createdOrder.getId());
        Assertions.assertEquals("customer123", createdOrder.getCustomerId());
        Assertions.assertFalse(createdOrder.isPaid());
    }

    @Test
    public void testFetchOrders() {
        Mockito.when(orderRepository.findByIdInAndCustomerId(ArgumentMatchers.any(), ArgumentMatchers.anyString()))
                .thenReturn(new ArrayList<>());
        Mockito.when(orderRepository.findByCustomerId(ArgumentMatchers.anyString()))
                .thenReturn(new ArrayList<>());
        Mockito.when(orderRepository.findAllById(ArgumentMatchers.any()))
                .thenReturn(new ArrayList<>());
        Mockito.when(orderRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<Order> orders1 = orderService.fetchOrders(null, "customer123");
        List<Order> orders2 = orderService.fetchOrders(new ArrayList<>(), "customer123");
        List<Order> orders3 = orderService.fetchOrders(new ArrayList<>(), null);
        List<Order> orders4 = orderService.fetchOrders(null, null);

        Assertions.assertNotNull(orders1);
        Assertions.assertNotNull(orders2);
        Assertions.assertNotNull(orders3);
        Assertions.assertNotNull(orders4);
    }

    @Test
    public void testUpdateOrder() {
        Order existingOrder = new Order();
        existingOrder.setId("1");
        existingOrder.setCustomerId("customer123");
        existingOrder.setPaid(false);

        Mockito.when(orderRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(existingOrder));
        Mockito.when(orderRepository.save(ArgumentMatchers.any())).thenAnswer(invocation -> invocation.getArgument(0));

        Order updatedOrder = new Order();
        updatedOrder.setPaid(true);
        Order result = orderService.updateOrder("1", updatedOrder);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPaid());
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order();
        order.setId("1");
        order.setCustomerId("customer123");
        order.setPaid(false);

        Mockito.when(orderRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderById("1");

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("1", result.get().getId());
    }
}
