package com.rest.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.springapp.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    // Find all feedbacks with pagination and sorting
    Page<Feedback> findAll(Pageable pageable);

    // Find feedbacks by restaurantId
    @Query("SELECT f FROM Feedback f WHERE f.restaurant.id = :restaurantId")
    Page<Feedback> findByRestaurantId(Long restaurantId, Pageable pageable);

    // Find feedbacks by userId
    @Query("SELECT f FROM Feedback f WHERE f.user.id = :userId")
    Page<Feedback> findByUserId(Long userId, Pageable pageable);

    // Search feedbacks by content
    @Query("SELECT f FROM Feedback f WHERE LOWER(f.content) LIKE LOWER(CONCAT('%', :content, '%'))")
    Page<Feedback> findByContentContaining(String content, Pageable pageable);
}
