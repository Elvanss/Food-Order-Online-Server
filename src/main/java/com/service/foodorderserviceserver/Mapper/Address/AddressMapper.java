package com.service.foodorderserviceserver.Mapper.Address;

import com.service.foodorderserviceserver.DTO.Address.AddressDTO;
import com.service.foodorderserviceserver.Entity.Address;
import com.service.foodorderserviceserver.Mapper.RestaurantMapper;
import com.service.foodorderserviceserver.Mapper.User.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;

    public AddressMapper(UserMapper userMapper, RestaurantMapper restaurantMapper) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
    }

    public AddressDTO convertToDto(Address source) {
        return new AddressDTO(source.getId(),
                               source.getBname(),
                               source.getStreet(),
                               source.getSuburb(),
                               source.getState(),
                               source.getPostCode(),
                               source.getUser() != null ? userMapper.convertToDto(source.getUser()) : null,
                               source.getRestaurant() != null ? restaurantMapper.convertToDto(source.getRestaurant()) : null);
    }

    public Address convertToEntity(AddressDTO source) {
        Address address = new Address();
        address.setBname(source.getBname());
        address.setStreet(source.getStreet());
        address.setSuburb(source.getSuburb());
        address.setState(source.getState());
        address.setPostCode(source.getPostCode());
        return address;
    }
}
