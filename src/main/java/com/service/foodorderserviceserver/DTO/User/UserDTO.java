package com.service.foodorderserviceserver.DTO.User;

import com.service.foodorderserviceserver.DTO.Address.AddressDTO;
import com.service.foodorderserviceserver.DTO.Address.CustomerAddressDTO;
import com.service.foodorderserviceserver.Entity.Address.CustomerAddress;
import com.service.foodorderserviceserver.Entity.Type.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Roles roles;
    private Integer numberOfAddress;

    public UserDTO(Integer id,
                   String firstName,
                   String lastName,
                   String username,
                   String password,
                   String email,
                   String phone,
                   Roles roles,
                   Integer numberOfAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.numberOfAddress = numberOfAddress;
    }

//    public UserDTO(Integer id, String firstName, String lastName, String userName, String password, String email, String phoneNumber, Roles type, List<CustomerAddress> addresses) {
//    }
}

