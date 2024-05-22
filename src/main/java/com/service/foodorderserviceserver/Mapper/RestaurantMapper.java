package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.RestaurantDTO;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Utils.GeoLocation;
import org.springframework.stereotype.Component;


@Component
public class RestaurantMapper {

    public RestaurantDTO convertToDto (Restaurant source) {
        return new RestaurantDTO(source.getId(),
                                 source.getRestaurantName(),
                                 source.getEmail(),
                                 source.getPassword(),
                                 source.getPhone(),
                                 source.getCuisine(),
                                 source.getOpenTime(),
                                 source.getCloseTime(),
                                 source.isOpened(),
                                 source.getDescription(),
                                 0,
                                 source.getNumberOfAddress(),
                                 source.getNumberOfItems());
    }

    public RestaurantDTO convertToDtoByDistance(GeoLocation.RestaurantDistance source) {
        RestaurantDTO restaurantDTO = convertToDto(source.getRestaurant());
        restaurantDTO.setDistance(source.getDistance());
        return restaurantDTO;
    }

    public Restaurant convertToEntity (RestaurantDTO source) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(source.getRestaurantName());
        restaurant.setEmail(source.getEmail());
        restaurant.setPassword(source.getPassword());
        restaurant.setPhone(source.getPhone());
        restaurant.setCuisine(source.getCuisine());
        restaurant.setOpenTime(source.getOpenTime());
        restaurant.setCloseTime(source.getCloseTime());
        restaurant.setOpened(source.isOpened());
        restaurant.setDescription(source.getDescription());
        return restaurant;
    }

}

