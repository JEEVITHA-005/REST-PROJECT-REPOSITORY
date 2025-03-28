package com.rest.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.Restaurant;
import com.rest.springapp.model.User;
import com.rest.springapp.service.RestaurantService;
import com.rest.springapp.service.UserService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;  // ✅ Ensure users exist before creating feedback

    // ✅ Create a new restaurant
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        // Ensure all users in feedback exist
        restaurant.getFeedbacks().forEach(feedback -> {
            User user = userService.getUserById(feedback.getUser().getId());
            if (user == null) {
                throw new RuntimeException("User with ID " + feedback.getUser().getId() + " does not exist");
            }
            feedback.setUser(user);
        });

        Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant);
        return ResponseEntity.ok(savedRestaurant);
    }

    // ✅ Update an existing restaurant
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        // Ensure the restaurant exists
        Restaurant existingRestaurant = restaurantService.getRestaurantById(id);
        if (existingRestaurant == null) {
            return ResponseEntity.notFound().build();
        }

        // Ensure all users in feedback exist
        restaurant.getFeedbacks().forEach(feedback -> {
            User user = userService.getUserById(feedback.getUser().getId());
            if (user == null) {
                throw new RuntimeException("User with ID " + feedback.getUser().getId() + " does not exist");
            }
            feedback.setUser(user);
        });

        restaurant.setId(id); // Ensure ID matches
        Restaurant updatedRestaurant = restaurantService.saveRestaurant(restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    // ✅ Get restaurant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return restaurant != null ? ResponseEntity.ok(restaurant) : ResponseEntity.notFound().build();
    }

    // ✅ Get all restaurants with pagination
    @GetMapping
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return restaurantService.getAllRestaurants(pageable);
    }

    // ✅ JPQL Query - Get restaurants by name
    @GetMapping("/search")
    public Page<Restaurant> getRestaurantsByName(@RequestParam String name, Pageable pageable) {
        return restaurantService.getRestaurantsByName(name, pageable);
    }

    // ✅ Delete a restaurant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
