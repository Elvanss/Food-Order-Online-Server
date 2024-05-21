package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.AddressDTO;
import com.service.foodorderserviceserver.Entity.Address;
import com.service.foodorderserviceserver.Mapper.AddressMapper;
import com.service.foodorderserviceserver.Service.AddressService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @PostMapping
    public Result createAddress(@RequestBody AddressDTO addressDTO) {
        Address address = addressMapper.convertToEntity(addressDTO);
        Address newAddress = addressService.createAddress(address);
        AddressDTO newAddressDTO = addressMapper.convertToDto(newAddress);
        return new Result(true, StatusCode.SUCCESS, "Address created successfully", newAddressDTO);
    }

    @PutMapping("/{id}")
    public Result updateAddress(@PathVariable Integer id, @RequestBody AddressDTO addressDTO) {
        Address address = addressMapper.convertToEntity(addressDTO);
        Address updatedAddress = addressService.updateAddress(id, address);
        AddressDTO updatedAddressDTO = addressMapper.convertToDto(updatedAddress);
        return new Result(true, StatusCode.SUCCESS, "Address updated successfully", updatedAddressDTO);
    }

    @DeleteMapping("/{id}")
    public Result deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return new Result(true, StatusCode.SUCCESS, "Address deleted successfully", null);
    }

    
}
