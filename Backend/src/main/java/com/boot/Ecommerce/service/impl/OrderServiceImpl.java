package com.boot.Ecommerce.service.impl;

import com.boot.Ecommerce.entity.Order;
import com.boot.Ecommerce.repository.OrderRepository;
import com.boot.Ecommerce.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> fetchOrders(List<String> ids, String customerId) {
        if (ids != null && !ids.isEmpty() && customerId != null) {
            return orderRepository.findByIdInAndCustomerId(ids, customerId);
        } else if (customerId != null){
            return orderRepository.findByCustomerId(customerId);
        } else if (ids != null && !ids.isEmpty()) {
            return orderRepository.findAllById(ids);
        } else {
            return orderRepository.findAll();
        }
    }

    @Override
    public Order updateOrder(String id, Order updatedOrder) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()){
            Order orderUpdated = existingOrder.get();
            if (updatedOrder.isPaid()) {
                orderUpdated.setPaid(true);
            }
            if (Objects.nonNull(updatedOrder.getCustomerId())){
                orderUpdated.setCustomerId(updatedOrder.getCustomerId());
            }
            if (Objects.nonNull(updatedOrder.getProducts())){
                orderUpdated.setProducts(updatedOrder.getProducts());
            }
            if (updatedOrder.getTotal() != 0.0) {
                orderUpdated.setTotal(updatedOrder.getTotal());
            }

            return orderRepository.save(orderUpdated);
        } else {
            throw new EntityNotFoundException("Order with ID: " + id + " not found");
        }
    }

    @Override
    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }
}
