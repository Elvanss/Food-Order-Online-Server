package com.service.foodorderserviceserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddressDTO {
    private Integer id;
    private String bname;
    private String street;
    private String suburb;
    private String state;
    private String postCode;    
    private UserDTO userId;
    private RestaurantDTO restaurantId;
}


