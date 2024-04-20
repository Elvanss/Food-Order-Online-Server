package com.service.foodorderserviceserver.DTO.Address;

import com.service.foodorderserviceserver.DTO.RestaurantDTO;
import com.service.foodorderserviceserver.DTO.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class RestaurantAddressDTO extends AddressDTO {

    public RestaurantAddressDTO(Integer id, String bname, String street, String suburb, String state, String postCode) {
        super(id, bname, street, suburb, state, postCode);
    }
}
