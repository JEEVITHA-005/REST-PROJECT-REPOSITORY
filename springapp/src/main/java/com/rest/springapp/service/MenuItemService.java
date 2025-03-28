package com.rest.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.MenuItem;
import com.rest.springapp.repository.MenuItemRepository;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    // Create or update a menu item
    public MenuItem saveMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    // Get a specific menu item by ID
    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    // Get all menu items with pagination and sorting
    public Page<MenuItem> getAllMenuItems(Pageable pageable) {
        return menuItemRepository.findAll(pageable);
    }

    // Get menu items by restaurantId with pagination and sorting
    public Page<MenuItem> getMenuItemsByRestaurantId(Long restaurantId, Pageable pageable) {
        return menuItemRepository.findByRestaurantId(restaurantId, pageable);
    }

    // Get menu items by name with pagination and sorting
    public Page<MenuItem> getMenuItemsByName(String name, Pageable pageable) {
        return menuItemRepository.findByNameContaining(name, pageable);
    }

    // Delete a menu item by ID
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}
