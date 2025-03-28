package com.rest.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private Long orderId;
    private Long menuItemId;

    // ✅ Add a No-Args Constructor (Required for JPA)
    public OrderItem() {
    }

    // ✅ Add Constructor for Easy Initialization
    public OrderItem(Long id, int quantity, Long orderId, Long menuItemId) {
        this.id = id;
        this.quantity = quantity;
        this.orderId = orderId;
        this.menuItemId = menuItemId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }
}
