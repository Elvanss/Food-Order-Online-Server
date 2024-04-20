package com.service.foodorderserviceserver.DTO.Address;

import com.service.foodorderserviceserver.DTO.User.UserDTO;
import com.service.foodorderserviceserver.Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class CustomerAddressDTO extends AddressDTO {

    private UserDTO user;

    public CustomerAddressDTO(Integer id,
                              String bname,
                              String street,
                              String suburb,
                              String state,
                              String postCode,
                              UserDTO user) {
        super(id, bname, street, suburb, state, postCode);
        this.user = user;

    }
}