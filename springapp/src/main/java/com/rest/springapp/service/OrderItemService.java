package com.rest.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.OrderItem;
import com.rest.springapp.repository.OrderItemRepository;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Create or update an order item
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Get a specific order item by ID
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    // Get all order items with pagination and sorting
    public Page<OrderItem> getAllOrderItems(Pageable pageable) {
        return orderItemRepository.findAll(pageable);
    }

    // Get order items by orderId with pagination and sorting
    public Page<OrderItem> getOrderItemsByOrderId(Long orderId, Pageable pageable) {
        return orderItemRepository.findByOrderId(orderId, pageable);
    }

    // Get order items by menuItemId with pagination and sorting
    public Page<OrderItem> getOrderItemsByMenuItemId(Long menuItemId, Pageable pageable) {
        return orderItemRepository.findByMenuItemId(menuItemId, pageable);
    }

    // Delete an order item by ID
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
