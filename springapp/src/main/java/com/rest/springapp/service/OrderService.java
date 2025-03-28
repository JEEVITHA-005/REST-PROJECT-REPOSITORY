package com.rest.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.OrderEntity;
import com.rest.springapp.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Create or update an order
    public OrderEntity saveOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    // Get a specific order by ID
    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    // Get all orders with pagination and sorting
    public Page<OrderEntity> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    // Get orders by userId with pagination and sorting
    public Page<OrderEntity> getOrdersByUserId(Long userId, Pageable pageable) {
        return orderRepository.findByUserId(userId, pageable);
    }

    // ✅ Fix: Correct reference for restaurant ID
    public Page<OrderEntity> getOrdersByRestaurantId(Long restaurantId, Pageable pageable) {
        return orderRepository.findByRestaurantId(restaurantId, pageable);
    }

    // Delete an order
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
