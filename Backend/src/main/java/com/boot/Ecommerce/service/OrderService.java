package com.boot.Ecommerce.service;
import com.boot.Ecommerce.entity.Order;
import java.util.*;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> fetchOrders(List<String> ids, String customerId);
    Order updateOrder(String id, Order updatedOrder);
    Optional<Order> getOrderById(String id);
}
