package com.rest.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.Feedback;
import com.rest.springapp.service.FeedbackService;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public Feedback createOrUpdateFeedback(@RequestBody Feedback feedback) {
        return feedbackService.saveFeedback(feedback);
    }

    @PutMapping("/{id}")
    public Feedback updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        feedback.setId(id);
        return feedbackService.saveFeedback(feedback);
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }

    @GetMapping
    public Page<Feedback> getAllFeedbacks(Pageable pageable) {
        return feedbackService.getAllFeedbacks(pageable);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Page<Feedback> getFeedbacksByRestaurantId(@PathVariable Long restaurantId, Pageable pageable) {
        return feedbackService.getFeedbacksByRestaurantId(restaurantId, pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }
}
