package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rest.springapp.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // Find all restaurants with pagination and sorting
    @SuppressWarnings("null")
    Page<Restaurant> findAll(Pageable pageable);

    // Find restaurant by name (Custom JPQL Query)
    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE %:name%")
    Page<Restaurant> findByNameContaining(String name, Pageable pageable);
}
