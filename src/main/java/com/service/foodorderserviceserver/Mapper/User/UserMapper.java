package com.service.foodorderserviceserver.Mapper.User;

import com.service.foodorderserviceserver.DTO.User.UserDTO;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Mapper.Address.AddressMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
                            source.getType(),
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
        user.setType(source.getRoles());
    return user;
}
}
