package com.rest.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.MenuItem;
import com.rest.springapp.service.MenuItemService;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.saveMenuItem(menuItem);
    }

    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        menuItem.setId(id);
        return menuItemService.saveMenuItem(menuItem);
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @GetMapping
    public Page<MenuItem> getAllMenuItems(Pageable pageable) {
        return menuItemService.getAllMenuItems(pageable);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Page<MenuItem> getMenuItemsByRestaurantId(@PathVariable Long restaurantId, Pageable pageable) {
        return menuItemService.getMenuItemsByRestaurantId(restaurantId, pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }
}
