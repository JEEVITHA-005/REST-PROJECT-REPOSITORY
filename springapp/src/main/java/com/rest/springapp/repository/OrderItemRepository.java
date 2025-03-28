package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rest.springapp.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Find all order items with pagination and sorting
    @SuppressWarnings("null")
    Page<OrderItem> findAll(Pageable pageable);

    // ✅ Fix JPQL Queries to Use Named Parameters Correctly
    @Query("SELECT oi FROM OrderItem oi WHERE oi.orderId = :orderId")
    Page<OrderItem> findByOrderId(@org.springframework.data.repository.query.Param("orderId") Long orderId, Pageable pageable);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.menuItemId = :menuItemId")
    Page<OrderItem> findByMenuItemId(@org.springframework.data.repository.query.Param("menuItemId") Long menuItemId, Pageable pageable);
}
