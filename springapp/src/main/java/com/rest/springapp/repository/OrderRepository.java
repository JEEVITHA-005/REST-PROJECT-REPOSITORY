package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springapp.model.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // Find all orders with pagination and sorting
    Page<OrderEntity> findAll(Pageable pageable);

    // ✅ Fix: Use o.user.id instead of o.userId
    @Query("SELECT o FROM OrderEntity o WHERE o.user.id = :userId ORDER BY o.orderDate DESC")
    Page<OrderEntity> findByUserId(Long userId, Pageable pageable);

    // ✅ Fix: Use o.restaurantId instead of o.restaurant.id (because restaurant is stored as Long)
    @Query("SELECT o FROM OrderEntity o WHERE o.restaurantId = :restaurantId ORDER BY o.orderDate DESC")
    Page<OrderEntity> findByRestaurantId(Long restaurantId, Pageable pageable);
}
