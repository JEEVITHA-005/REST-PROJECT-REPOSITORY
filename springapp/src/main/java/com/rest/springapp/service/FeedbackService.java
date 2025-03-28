package com.rest.springapp.service;

import com.rest.springapp.model.Feedback;
import com.rest.springapp.model.Restaurant;
import com.rest.springapp.repository.FeedbackRepository;
import com.rest.springapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private RestaurantRepository restaurantRepository; // ✅ Ensure Restaurant exists

    // ✅ Create or update feedback with Restaurant validation
    public Feedback saveFeedback(Feedback feedback) {
        Long restaurantId = feedback.getRestaurant().getId();
        
        // ✅ Ensure Restaurant exists
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant with ID " + restaurantId + " does not exist"));
        
        feedback.setRestaurant(restaurant); // ✅ Assign existing Restaurant to Feedback

        return feedbackRepository.save(feedback);
    }

    // ✅ Get a specific feedback by ID
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    // ✅ Get all feedbacks with pagination and sorting
    public Page<Feedback> getAllFeedbacks(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    // ✅ Get feedbacks by restaurantId with pagination and sorting
    public Page<Feedback> getFeedbacksByRestaurantId(Long restaurantId, Pageable pageable) {
        return feedbackRepository.findByRestaurantId(restaurantId, pageable);
    }

    // ✅ Get feedbacks by userId with pagination and sorting
    public Page<Feedback> getFeedbacksByUserId(Long userId, Pageable pageable) {
        return feedbackRepository.findByUserId(userId, pageable);
    }

    // ✅ Get feedbacks by content with pagination and sorting
    public Page<Feedback> getFeedbacksByContent(String content, Pageable pageable) {
        return feedbackRepository.findByContentContaining(content, pageable);
    }

    // ✅ Delete a feedback by ID
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
