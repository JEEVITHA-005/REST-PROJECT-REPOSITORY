package com.rest.springapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    // ✅ Many-to-One Relationship with Restaurant
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)  // Ensures it's NOT NULL
    @JsonBackReference  // Prevents infinite recursion
    private Restaurant restaurant;

    // ✅ Many-to-One Relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Ensures it's NOT NULL
    private User user;

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
