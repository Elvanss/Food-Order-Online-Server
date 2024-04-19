package com.service.foodorderserviceserver.Mapper.User;

import com.service.foodorderserviceserver.DTO.User.UserDTO;
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
                            source.getType());
    }

    public User convertToEntity(UserDTO source) {
        User user = new User();
        user.setUserName(source.getUsername());
        user.setEmail(source.getEmail());
        user.setPassword(source.getPassword());
        user.setType(source.getRoles());
        return user;
    }
}
