package com.service.foodorderserviceserver.Mapper.Address;

import com.service.foodorderserviceserver.DTO.Address.AddressDTO;
import com.service.foodorderserviceserver.DTO.Address.CustomerAddressDTO;
import com.service.foodorderserviceserver.DTO.Address.RestaurantAddressDTO;
import com.service.foodorderserviceserver.Entity.Address.Address;
import com.service.foodorderserviceserver.Entity.Address.CustomerAddress;
import com.service.foodorderserviceserver.Entity.Address.RestaurantAddress;
import com.service.foodorderserviceserver.Mapper.RestaurantMapper;
import com.service.foodorderserviceserver.Mapper.User.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    private final UserMapper userMapper;

    public AddressMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public AddressDTO convertToDto(Address source) {
        return new AddressDTO(source.getId(),
                               source.getBname(),
                               source.getStreet(),
                               source.getSuburb(),
                               source.getState(),
                               source.getPostCode());
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

    public CustomerAddressDTO convertToCusAddressDto(CustomerAddress address) {
        CustomerAddressDTO dto = new CustomerAddressDTO();
        dto.setId(address.getId());
        dto.setBname(address.getBname());
        dto.setStreet(address.getStreet());
        dto.setSuburb(address.getSuburb());
        dto.setState(address.getState());
        dto.setPostCode(address.getPostCode());
        dto.setUser(userMapper.convertToDto(address.getUser()));
        return dto;
    }

    public RestaurantAddressDTO convertToResAddressDTO(RestaurantAddress address) {
        RestaurantAddressDTO dto = new RestaurantAddressDTO();
        dto.setId(address.getId());
        dto.setBname(address.getBname());
        dto.setStreet(address.getStreet());
        dto.setSuburb(address.getSuburb());
        dto.setState(address.getState());
        dto.setPostCode(address.getPostCode());
        return dto;
    }

    public CustomerAddress convertToCusAddressEntity(CustomerAddressDTO addressDTO) {
        CustomerAddress address = new CustomerAddress();
        address.setBname(addressDTO.getBname());
        address.setStreet(addressDTO.getStreet());
        address.setSuburb(addressDTO.getSuburb());
        address.setState(addressDTO.getState());
        address.setPostCode(addressDTO.getPostCode());
        return address;
    }

    public RestaurantAddress convertToResAddressEntity(RestaurantAddressDTO addressDTO) {
        RestaurantAddress address = new RestaurantAddress();
        address.setBname(addressDTO.getBname());
        address.setStreet(addressDTO.getStreet());
        address.setSuburb(addressDTO.getSuburb());
        address.setState(addressDTO.getState());
        address.setPostCode(addressDTO.getPostCode());
        return address;
    }
}
