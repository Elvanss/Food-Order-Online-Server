package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Feedback;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.FeedbackRepository;
import com.service.foodorderserviceserver.System.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> findAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback createFeedback(Feedback newFeedback, User user, Restaurant restaurant) {
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setRestaurant(restaurant);
        feedback.setContent(newFeedback.getContent());
        feedback.setRating(newFeedback.getRating());
        feedback.setPostDateTime(newFeedback.getPostDateTime());
        return this.feedbackRepository.save(feedback);
    }

    public Feedback findById(Integer feedbackId) {
        return this.feedbackRepository.findById(feedbackId).orElseThrow(() -> new ObjectNotFoundException("feed", feedbackId));
    }

    public Feedback updateFeedback(Integer id, Feedback newFeedback) {
        Feedback existingFeedback = this.feedbackRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("feed", id));
        existingFeedback.setUser(newFeedback.getUser());
        existingFeedback.setRestaurant(newFeedback.getRestaurant());
        existingFeedback.setContent(newFeedback.getContent());
        existingFeedback.setRating(newFeedback.getRating());
        existingFeedback.setPostDateTime(newFeedback.getPostDateTime());
        return this.feedbackRepository.save(existingFeedback);
    }

    public Feedback saveFeedback(Feedback feedback) {
        return this.feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackByCustomer(User user) {
        return this.feedbackRepository.findByUser(user);
    }

    public List<Feedback> getFeedbackByRestaurant(Restaurant restaurant) {
        return this.feedbackRepository.findByRestaurant(restaurant);
    }

}
