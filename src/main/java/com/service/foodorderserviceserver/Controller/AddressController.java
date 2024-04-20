package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.Address.AddressDTO;
import com.service.foodorderserviceserver.DTO.Address.CustomerAddressDTO;
import com.service.foodorderserviceserver.DTO.Address.RestaurantAddressDTO;
import com.service.foodorderserviceserver.Entity.Address.Address;
import com.service.foodorderserviceserver.Entity.Address.CustomerAddress;
import com.service.foodorderserviceserver.Entity.Address.RestaurantAddress;
import com.service.foodorderserviceserver.Mapper.Address.AddressMapper;
import com.service.foodorderserviceserver.Service.AddressService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;


    public AddressController(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    /* Address System */
    @GetMapping
    public Result getAllAddress() {
        List<Address> address = addressService.getAllAddresses();
        List<AddressDTO> addressDTOS = address.stream()
                .map(this.addressMapper::convertToDto)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Address retrieved successfully", addressDTOS);
    }

    @GetMapping("/{id}")
    public Result getAddressById(@PathVariable Integer id) {
        Address address = addressService.getAddressById(id);
        AddressDTO addressDTO = addressMapper.convertToDto(address);
        return new Result(true, StatusCode.SUCCESS, "Address retrieved successfully", addressDTO);
    }

    @DeleteMapping("/{id}")
    public Result deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return new Result(true, StatusCode.SUCCESS, "Address deleted successfully", null);
    }

    /* Customer Address System */
    @GetMapping("/user/{userId}")
    public Result getAllUserAddress(@PathVariable Integer userId) {
        Optional<CustomerAddress> address = addressService.getAllsUserAddresses(userId);
        List<CustomerAddressDTO> addressDTOS = address.stream()
                .map(this.addressMapper::convertToCusAddressDto)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "User Address retrieved successfully", addressDTOS);
    }

    @GetMapping("/user/{userId}/{addressId}")
    public Result getUserAddressById(@PathVariable Integer userId, @PathVariable Integer addressId) {
        CustomerAddress address = addressService.getUserAddressesById(userId, addressId);
        CustomerAddressDTO addressDTO = addressMapper.convertToCusAddressDto(address);
        return new Result(true, StatusCode.SUCCESS, "User Address retrieved successfully", addressDTO);
    }

    @PostMapping("/user/{userId}")
    public Result createUserAddress(@PathVariable Integer userId, @RequestBody CustomerAddressDTO addressDTO) {
        CustomerAddress address = addressMapper.convertToCusAddressEntity(addressDTO);
        CustomerAddress createdAddress = addressService.createUserAddress(userId, address);
        CustomerAddressDTO createdAddressDTO = addressMapper.convertToCusAddressDto(createdAddress);
        return new Result(true, StatusCode.SUCCESS, "User Address created successfully", createdAddressDTO);
    }

    @PutMapping("/user/{userId}/{addressId}")
    public Result updateUserAddress(@PathVariable Integer userId, @PathVariable Integer addressId, @RequestBody CustomerAddressDTO addressDTO) {
        CustomerAddress address = addressMapper.convertToCusAddressEntity(addressDTO);
        CustomerAddress updatedAddress = addressService.updateUserAddress(userId, addressId, address);
        CustomerAddressDTO updatedAddressDTO = addressMapper.convertToCusAddressDto(updatedAddress);
        return new Result(true, StatusCode.SUCCESS, "User Address updated successfully", updatedAddressDTO);
    }

    @DeleteMapping("/user/{userId}/{addressId}")
    public Result deleteUserAddress(@PathVariable Integer userId, @PathVariable Integer addressId) {
        addressService.deleteUserAddress(userId, addressId);
        return new Result(true, StatusCode.SUCCESS, "User Address deleted successfully", null);
    }

    /* Restaurant Address System */
    @GetMapping("/restaurant/{restaurantId}")
    public Result getRestaurantAddressById(@PathVariable Integer restaurantId) {
        RestaurantAddress address = addressService.getRestaurantAddressById(restaurantId);
        RestaurantAddressDTO addressDTO = addressMapper.convertToResAddressDTO(address);
        return new Result(true, StatusCode.SUCCESS, "Restaurant Address retrieved successfully", addressDTO);
    }

    @PostMapping("/restaurant/{restaurantId}")
    public Result createRestaurantAddress(@PathVariable Integer restaurantId, @RequestBody RestaurantAddressDTO addressDTO) {
        RestaurantAddress address = addressMapper.convertToResAddressEntity(addressDTO);
        RestaurantAddress createdAddress = addressService.createRestaurantAddress(restaurantId, address);
        RestaurantAddressDTO createdAddressDTO = addressMapper.convertToResAddressDTO(createdAddress);
        return new Result(true, StatusCode.SUCCESS, "Restaurant Address created successfully", createdAddressDTO);
    }

    @PutMapping("/restaurant/{restaurantId}")
    public Result updateRestaurantAddress(@PathVariable Integer restaurantId, @RequestBody RestaurantAddressDTO addressDTO) {
        RestaurantAddress address = addressMapper.convertToResAddressEntity(addressDTO);
        RestaurantAddress updatedAddress = addressService.updateRestaurantAddress(restaurantId, address);
        RestaurantAddressDTO updatedAddressDTO = addressMapper.convertToResAddressDTO(updatedAddress);
        return new Result(true, StatusCode.SUCCESS, "Restaurant Address updated successfully", updatedAddressDTO);
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public Result deleteRestaurantAddress(@PathVariable Integer restaurantId) {
        addressService.deleteRestaurantAddress(restaurantId);
        return new Result(true, StatusCode.SUCCESS, "Restaurant Address deleted successfully", null);
    }
}
