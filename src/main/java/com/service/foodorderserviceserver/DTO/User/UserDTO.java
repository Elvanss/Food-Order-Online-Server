package com.service.foodorderserviceserver.DTO.User;

import com.service.foodorderserviceserver.Entity.Type.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}

