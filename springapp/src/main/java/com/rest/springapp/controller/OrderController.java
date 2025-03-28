package com.rest.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.OrderEntity;
import com.rest.springapp.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Create a new order
    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderEntity order) {
        return orderService.saveOrder(order);
    }

    // Update an existing order
    @PutMapping("/{id}")
    public OrderEntity updateOrder(@PathVariable Long id, @RequestBody OrderEntity order) {
        OrderEntity existingOrder = orderService.getOrderById(id);
        if (existingOrder == null) {
            throw new RuntimeException("Order with ID " + id + " not found");
        }

        // Update only the fields that need modification
        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setStatus(order.getStatus());
        existingOrder.setId(order.getId());
        existingOrder.setRestaurantId(order.getRestaurantId());

        return orderService.saveOrder(existingOrder);
    }

    // Get order by ID
    @GetMapping("/{id}")
    public OrderEntity getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // Get all orders with pagination and sorting
    @GetMapping
    public Page<OrderEntity> getAllOrders(Pageable pageable) {
        return orderService.getAllOrders(pageable);
    }

    // Get orders by userId with pagination and sorting
    @GetMapping("/user/{userId}")
    public Page<OrderEntity> getOrdersByUserId(@PathVariable Long userId, Pageable pageable) {
        return orderService.getOrdersByUserId(userId, pageable);
    }

    // Get orders by restaurantId with pagination and sorting
    @GetMapping("/restaurant/{restaurantId}")
    public Page<OrderEntity> getOrdersByRestaurantId(@PathVariable Long restaurantId, Pageable pageable) {
        return orderService.getOrdersByRestaurantId(restaurantId, pageable);
    }

    // Delete an order by ID
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    
}
