package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.Entity.Type.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedbackDTO {

    private Integer id;
    private UserDTO user;
    private RestaurantDTO restaurant;
    private String content;
    private Rating rating;
    private LocalDateTime postDateTime;

}
