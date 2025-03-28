package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rest.springapp.model.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // Find all menu items with pagination and sorting
    Page<MenuItem> findAll(Pageable pageable);

    // ✅ Fix: Use `m.restaurant.id` instead of `m.restaurantId`
    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id = :restaurantId")
    Page<MenuItem> findByRestaurantId(Long restaurantId, Pageable pageable);

    // Find menu items by name (Custom JPQL Query)
    @Query("SELECT m FROM MenuItem m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<MenuItem> findByNameContaining(String name, Pageable pageable);
}
