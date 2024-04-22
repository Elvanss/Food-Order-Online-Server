package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.FeedbackDTO;
import com.service.foodorderserviceserver.Entity.Feedback;
import com.service.foodorderserviceserver.Mapper.FeedbackMapper;
import com.service.foodorderserviceserver.Service.FeedbackService;
import com.service.foodorderserviceserver.Service.RestaurantService;
import com.service.foodorderserviceserver.Service.UserService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedbackControllerTest {

    @InjectMocks
    private FeedbackController feedbackController;

    @Mock
    private FeedbackService feedbackService;

    @Mock
    private FeedbackMapper feedbackMapper;

    @Mock
    private UserService userService;

    @Mock
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllFeedback() {

        // Arrange
        List<Feedback> feedbackList = new ArrayList<>();
        Feedback feedback = new Feedback();
        feedbackList.add(feedback);
        when(feedbackService.findAllFeedback()).thenReturn(feedbackList);
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        when(feedbackMapper.convert(feedback)).thenReturn(feedbackDTO);

        // Act
        Result result = feedbackController.findAllFeedback();

        // Assert
        assertTrue(result.isFlag());
        assertEquals(StatusCode.SUCCESS, result.getCode());
        assertEquals("Get all feedback successfully", result.getMessage());
        assertNotNull(result.getData());
        verify(feedbackService, times(1)).findAllFeedback();
        verify(feedbackMapper, times(1)).convert(feedback);

    }

    @Test
    void createFeedback() {
    }

    @Test
    void getFeedbackById() {
    }

    @Test
    void updateFeedback() {
    }

    @Test
    void getFeedbackByCustomer() {
    }

    @Test
    void getFeedbackByRestaurant() {
    }
}