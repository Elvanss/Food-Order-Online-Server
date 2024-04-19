package com.service.foodorderserviceserver.Mapper.Address;

import com.service.foodorderserviceserver.DTO.Address.AddressDTO;
import com.service.foodorderserviceserver.DTO.Address.CustomerAddressDTO;
import com.service.foodorderserviceserver.DTO.Address.RestaurantAddressDTO;
import com.service.foodorderserviceserver.Entity.Address.Address;
import com.service.foodorderserviceserver.Entity.Address.CustomerAddress;
import com.service.foodorderserviceserver.Entity.Address.RestaurantAddress;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

//    public AddressDTO convertToDto(Address source) {
//        return new AddressDTO(source.getId(),
//                               source.getBname(),
//                               source.getStreet(),
//                               source.getSuburb(),
//                               source.getState(),
//                               source.getPostCode());
//    }
//
//    public Address convertToEntity(AddressDTO source) {
//        Address address = new Address();
//        address.setBname(source.getBname());
//        address.setStreet(source.getStreet());
//        address.setSuburb(source.getSuburb());
//        address.setState(source.getState());
//        address.setPostCode(source.getPostCode());
//        return address;
//    }

    public CustomerAddressDTO convertToCusAddressDto(CustomerAddress address) {
        CustomerAddressDTO dto = new CustomerAddressDTO();
        dto.setId(address.getId());
        dto.setBuildingName(address.getBname());
        dto.setStreet(address.getStreet());
        dto.setSuburb(address.getSuburb());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostCode());
        dto.setUserId(address.getUser().getId());
        return dto;
    }

    public RestaurantAddressDTO convertToResAddressDTO(RestaurantAddress address) {
        RestaurantAddressDTO dto = new RestaurantAddressDTO();
        dto.setId(address.getId());
        dto.setBuildingName(address.getBname());
        dto.setStreet(address.getStreet());
        dto.setSuburb(address.getSuburb());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostCode());
        dto.setRestaurantId(address.getRestaurant().getId());
        return dto;
    }
}
