package com.rest.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.rest.springapp.model.Restaurant;
import com.rest.springapp.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Create or update a restaurant
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Get a specific restaurant by ID
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    // Get all restaurants with pagination and sorting
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    // Get restaurants by name with pagination and sorting
    public Page<Restaurant> getRestaurantsByName(String name, Pageable pageable) {
        return restaurantRepository.findByNameContaining(name, pageable);
    }

    // Delete a restaurant by ID
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
