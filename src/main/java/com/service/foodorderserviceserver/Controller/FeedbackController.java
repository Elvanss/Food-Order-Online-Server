package com.service.foodorderserviceserver.Controller;


import com.service.foodorderserviceserver.DTO.FeedbackDTO;
import com.service.foodorderserviceserver.Entity.Feedback;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Mapper.FeedbackMapper;
import com.service.foodorderserviceserver.Service.FeedbackService;
import com.service.foodorderserviceserver.Service.RestaurantService;
import com.service.foodorderserviceserver.Service.UserService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final FeedbackMapper feedbackMapper;
    private final UserService userService;
    private final RestaurantService restaurantService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, FeedbackMapper feedbackMapper, UserService userService, RestaurantService restaurantService) {
        this.feedbackService = feedbackService;
        this.feedbackMapper = feedbackMapper;
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public Result findAllFeedback() {
        List<Feedback> feedbackList = feedbackService.findAllFeedback();
        List<FeedbackDTO> feedbackDTOList = feedbackList.stream().map(this.feedbackMapper::convert).toList();
        return new Result(true, StatusCode.SUCCESS, "Get all feedback successfully", feedbackDTOList);
    }

    @PostMapping
    public Result createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        User user = feedbackDTO.getUser(); // Assuming User is received in the request
        Restaurant restaurant = feedbackDTO.getRestaurant(); // Assuming Restaurant is received in the request
        Feedback feedback = feedbackMapper.convertToEntity(feedbackDTO);
        Feedback createdFeedback = feedbackService.createFeedback(feedback, user, restaurant);
        return new Result(true, StatusCode.SUCCESS, "Feedback created successfully", feedbackMapper.convert(createdFeedback));
    }

    @GetMapping("/{id}")
    public Result getFeedbackById(@PathVariable Integer id) {
        Feedback feedback = feedbackService.findById(id);
        return new Result(true, StatusCode.SUCCESS, "Feedback retrieved successfully", feedbackMapper.convert(feedback));
    }

    @PutMapping("/{id}")
    public Result updateFeedback(@PathVariable Integer id, @RequestBody FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.convertToEntity(feedbackDTO);
        Feedback updatedFeedback = feedbackService.updateFeedback(id, feedback);
        return new Result(true, StatusCode.SUCCESS, "Feedback updated successfully", feedbackMapper.convert(updatedFeedback));
    }

    @GetMapping("/user/{userId}")
    public Result getFeedbackByCustomer(@PathVariable Integer userId) {
        //User user = new User(userId);
        User user = userService.findById(userId);
        List<Feedback> feedbacks = feedbackService.getFeedbackByCustomer(user);
        List<FeedbackDTO> feedbackDTOs = feedbacks.stream().map(feedbackMapper::convert).collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Feedback by customer retrieved successfully", feedbackDTOs);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public Result getFeedbackByRestaurant(@PathVariable Integer restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Feedback> feedbacks = feedbackService.getFeedbackByRestaurant(restaurant);
        List<FeedbackDTO> feedbackDTOs = feedbacks.stream().map(feedbackMapper::convert).collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Feedback by restaurant retrieved successfully", feedbackDTOs);
    }


}
