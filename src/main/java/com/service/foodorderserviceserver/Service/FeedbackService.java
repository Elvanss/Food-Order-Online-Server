package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Feedback;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.FeedbackRepository;
import com.service.foodorderserviceserver.Repository.RestaurantRepository;
import com.service.foodorderserviceserver.Repository.User.UserRepository;
import com.service.foodorderserviceserver.System.exception.CustomObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository,
                           UserRepository userRepository,
                           RestaurantRepository restaurantRepository) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Feedback> findAllFeedback() {
        return feedbackRepository.findAll();
    }

//    public Feedback createFeedback(Feedback newFeedback, User user, Restaurant restaurant) {
//        Feedback feedback = new Feedback();
//        feedback.setUser(user);
//        feedback.setRestaurant(restaurant);
//        feedback.setContent(newFeedback.getContent());
//        feedback.setRating(newFeedback.getRating());
//        feedback.setPostDateTime(newFeedback.getPostDateTime());
//        return this.feedbackRepository.save(feedback);
//    }
    public Feedback createFeedback(Feedback newFeedback, Integer user, Integer restaurant) {
        User userFeedback = userRepository.findById(user)
                .orElseThrow(() -> new CustomObjectNotFoundException("user", user));
        Restaurant restaurantFeedback = restaurantRepository.findById(restaurant)
                .orElseThrow(() -> new CustomObjectNotFoundException("restaurant", restaurant));
        Feedback feedback = new Feedback();
        feedback.setContent(newFeedback.getContent());
        feedback.setRating(newFeedback.getRating());
        feedback.setPostDateTime(newFeedback.getPostDateTime());
        feedback.setUser(userFeedback);
        feedback.setRestaurant(restaurantFeedback);
        return this.feedbackRepository.save(feedback);
    }

    public Feedback findById(Integer feedbackId) {
        return this.feedbackRepository.findById(feedbackId).orElseThrow(() -> new CustomObjectNotFoundException("feed", feedbackId));
    }

    public Feedback updateFeedback(Integer id, Feedback newFeedback) {
        Feedback existingFeedback = this.feedbackRepository.findById(id).orElseThrow(() -> new CustomObjectNotFoundException("feed", id));
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
