package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.UserDTO;
import com.service.foodorderserviceserver.Entity.User.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO convertToDto(User source) {

        return new UserDTO(source.getId(),
                            source.getFirstName(),
                            source.getLastName(),
                            source.getUserName(),
                            source.getPassword(),
                            source.getEmail(),
                            source.getPhoneNumber(),
                            source.getNumberOfAddress());

    }

    public User convertToEntity(UserDTO source) {
        User user = new User();
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setUserName(source.getUsername());
        user.setPassword(source.getPassword());
        user.setEmail(source.getEmail());
        user.setPhoneNumber(source.getPhoneNumber());
    return user;
}
}
