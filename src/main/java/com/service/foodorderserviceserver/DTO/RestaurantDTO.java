package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.Entity.Type.CuisineType;
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
    private CuisineType cuisine;
    private Time openTime;
    private Time closeTime;
    private boolean isOpened;
    private String description;
    private String imageURL;
    private double distance;
    private Integer numberOfAddress;
    private Integer numberOfItems;

}




