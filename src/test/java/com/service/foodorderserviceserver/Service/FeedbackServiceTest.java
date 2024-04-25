package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.DTO.FeedbackDTO;
import com.service.foodorderserviceserver.DTO.User.UserDTO;
import com.service.foodorderserviceserver.Entity.Feedback;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.Type.Rating;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Mapper.FeedbackMapper;
import com.service.foodorderserviceserver.Repository.FeedbackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @Mock
    FeedbackRepository feedbackRepository;

    @InjectMocks
    FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testCreateFeedback() {
        // Arrange
        User user = new User();
        Restaurant restaurant = new Restaurant();
        Feedback newFeedback = new Feedback();
        newFeedback.setContent("Excellent");
        newFeedback.setRating(Rating.FiveStar);
        newFeedback.setPostDateTime(LocalDateTime.now());

        Feedback savedFeedback = new Feedback();
        savedFeedback.setUser(user);
        savedFeedback.setRestaurant(restaurant);
        savedFeedback.setContent(newFeedback.getContent());
        savedFeedback.setRating(newFeedback.getRating());
        savedFeedback.setPostDateTime(newFeedback.getPostDateTime());

        when(feedbackRepository.save(any(Feedback.class))).thenReturn(savedFeedback);

        // Act
        Feedback result = feedbackService.createFeedback(newFeedback, user, restaurant);

        // Assert
        assertEquals(savedFeedback, result);
        verify(feedbackRepository, times(1)).save(any(Feedback.class));
    }

    @Test
    void findById() {
        User user = new User();
        Restaurant restaurant = new Restaurant();

        Feedback feedBack = new Feedback(123456, user, restaurant, "Vua phai", Rating.OneStar, LocalDateTime.now());

        given(feedbackRepository.findById(123456)).willReturn(Optional.of(feedBack));

        Feedback returnedFeedback = this.feedbackService.findById(123456);

        assertThat(returnedFeedback.getId()).isEqualTo(feedBack.getId());
        assertThat(returnedFeedback.getUser()).isEqualTo(feedBack.getUser());
        assertThat(returnedFeedback.getRestaurant()).isEqualTo(feedBack.getRestaurant());
        assertThat(returnedFeedback.getContent()).isEqualTo(feedBack.getContent());
        assertThat(returnedFeedback.getRating()).isEqualTo(feedBack.getRating());
        assertThat(returnedFeedback.getPostDateTime()).isEqualTo(feedBack.getPostDateTime());

        verify(feedbackRepository, times(1)).findById(123456);
    }

    @Test
    void saveFeedback() {
        User user = new User();
        Restaurant restaurant = new Restaurant();

        Feedback feedback = new Feedback(123456, user, restaurant, "Vua phai", Rating.OneStar, LocalDateTime.now());

        when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        Feedback savedFeedback = feedbackService.saveFeedback(feedback);

        assertNotNull(savedFeedback, "The saved feedback should not be null");
        assertEquals(feedback, savedFeedback, "The saved feedback should match the original feedback");

        verify(feedbackRepository, times(1)).save(feedback);
    }

    @Test
    void getFeedbackByCustomer() {
        User user = new User();
        Restaurant restaurant = new Restaurant();

        Feedback feedback = new Feedback(123456, user, restaurant, "Vua phai", Rating.OneStar, LocalDateTime.now());

        List<Feedback> feedbackList = Arrays.asList(feedback);
        when(feedbackRepository.findByUser(any(User.class))).thenReturn(feedbackList);

        // Act
        List<Feedback> returnedFeedbackList = feedbackService.getFeedbackByCustomer(user);

        // Assert
        assertNotNull(returnedFeedbackList, "The returned feedback list should not be null");
        assertEquals(feedbackList, returnedFeedbackList, "The returned feedback list should match the original feedback list");

        // Verify that findByUser method was called once
        verify(feedbackRepository, times(1)).findByUser(user);
    }

    @Test
    void getFeedbackByRestaurant() {
        User user = new User();
        Restaurant restaurant = new Restaurant();

        Feedback feedback = new Feedback(123456, user, restaurant, "Vua phai", Rating.OneStar, LocalDateTime.now());

        List<Feedback> feedbackList = Arrays.asList(feedback);
        when(feedbackRepository.findByRestaurant(any(Restaurant.class))).thenReturn(feedbackList);

        List<Feedback> returnedFeedbackList = feedbackService.getFeedbackByRestaurant(restaurant);

        assertNotNull(returnedFeedbackList, "The returned feedback list should not be null");
        assertEquals(feedbackList, returnedFeedbackList, "The returned feedback list should match the original feedback list");

        verify(feedbackRepository, times(1)).findByRestaurant(restaurant);
    }
}