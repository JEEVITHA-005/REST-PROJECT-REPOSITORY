package com.rest.springapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.OrderItem;
import com.rest.springapp.service.OrderItemService;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    // ✅ Constructor Injection (Fix for Bean Creation Issue)
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // Create or update an order item
    @PostMapping
    public OrderItem createOrUpdateOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.saveOrderItem(orderItem);
    }

    // ✅ Update an existing order item
    @PutMapping("/{id}")
    public OrderItem updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        orderItem.setId(id);
        return orderItemService.saveOrderItem(orderItem);
    }

    // Get order item by ID
    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    // Get all order items with pagination and sorting
    @GetMapping
    public Page<OrderItem> getAllOrderItems(Pageable pageable) {
        return orderItemService.getAllOrderItems(pageable);
    }

    // Get order items by orderId with pagination and sorting
    @GetMapping("/order/{orderId}")
    public Page<OrderItem> getOrderItemsByOrderId(@PathVariable Long orderId, Pageable pageable) {
        return orderItemService.getOrderItemsByOrderId(orderId, pageable);
    }

    // Get order items by menuItemId with pagination and sorting
    @GetMapping("/menu/{menuItemId}")
    public Page<OrderItem> getOrderItemsByMenuItemId(@PathVariable Long menuItemId, Pageable pageable) {
        return orderItemService.getOrderItemsByMenuItemId(menuItemId, pageable);
    }

    // Delete order item by ID
    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
    }
}
