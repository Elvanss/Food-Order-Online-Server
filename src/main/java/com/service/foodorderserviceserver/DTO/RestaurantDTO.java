package com.service.foodorderserviceserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantDTO {
    private Integer id;
    private String restaurantName;
    private String email;
    private String password;
    private String phone;
    private String cuisine;
    private Time openTime;
    private Time closeTime;
    private boolean isOpened;
    private String description;
    private Integer numberOfAddress;
    private Integer numberOfItems;
}




