package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.FeedbackDTO;
import com.service.foodorderserviceserver.Entity.Feedback;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Mapper.User.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FeedbackMapper implements Converter<Feedback, FeedbackDTO> {

    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;

    @Override
public FeedbackDTO convert(Feedback feedBack) {
    return new FeedbackDTO(feedBack.getId(),
            feedBack.getUser() != null
                    ? userMapper.convertToDto(feedBack.getUser())
                    : null,
            feedBack.getRestaurant() != null
                    ? restaurantMapper.convertToDto(feedBack.getRestaurant())
                    : null,
            feedBack.getContent(),
            feedBack.getRating(),
            feedBack.getPostDateTime());
}

    public Feedback convertToEntity(FeedbackDTO feedbackDTO) {
        User user = userMapper.convertToEntity(feedbackDTO.getUser());
        Restaurant restaurant = restaurantMapper.convertToEntity(feedbackDTO.getRestaurant());

        return new Feedback(
                feedbackDTO.getId(),
                user,
                restaurant,
                feedbackDTO.getContent(),
                feedbackDTO.getRating(),
                feedbackDTO.getPostDateTime()
        );
    }
}
