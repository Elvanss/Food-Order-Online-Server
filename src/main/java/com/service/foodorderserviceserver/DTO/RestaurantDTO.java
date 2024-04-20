package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.DTO.Address.AddressDTO;
import com.service.foodorderserviceserver.Entity.Type.Roles;
import lombok.*;

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
    private Roles roles;
    private AddressDTO address;
    private Integer numberOfItems;


}




