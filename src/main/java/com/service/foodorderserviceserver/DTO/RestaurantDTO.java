package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.DTO.Address.AddressDTO;
import com.service.foodorderserviceserver.DTO.Address.RestaurantAddressDTO;
import com.service.foodorderserviceserver.Entity.Type.Roles;
import lombok.Data;

import java.sql.Time;

@Data
public class RestaurantDTO {
    private Integer id;
    private String restaurantName;
    private String email;
    private String phone;
    private String cuisine;
    private Time openTime;
    private Time closeTime;
    private boolean isOpened;
    private String description;
    private Roles roles;
    private RestaurantAddressDTO address;
    private Integer numberOfItems;

    public RestaurantDTO(Integer id, String restaurantName, String email, String phone, String cuisine, Time openTime, Time closeTime, boolean isOpened, String description, Roles roles, RestaurantAddressDTO address, Integer numberOfItems) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.email = email;
        this.phone = phone;
        this.cuisine = cuisine;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.isOpened = isOpened;
        this.description = description;
        this.roles = roles;
        this.address = address;
        this.numberOfItems = numberOfItems;
    }
}




