package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Address.Address;
import com.service.foodorderserviceserver.Entity.Address.CustomerAddress;
import com.service.foodorderserviceserver.Entity.Address.RestaurantAddress;
import com.service.foodorderserviceserver.Repository.Address.AddressRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    // Show all the addresses in the system
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public Address createAddress(Integer addressId, Address address) {
        Address addressExist = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        addressExist.setBname(address.getBname());
        addressExist.setStreet(address.getStreet());
        addressExist.setSuburb(address.getSuburb());
        addressExist.setState(address.getState());
        addressExist.setPostCode(address.getPostCode());
        return addressRepository.save(address);
    }

    public Address updateAddress(Integer id, Address address) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        existingAddress.setBname(address.getBname());
        existingAddress.setStreet(address.getStreet());
        existingAddress.setSuburb(address.getSuburb());
        existingAddress.setState(address.getState());
        existingAddress.setPostCode(address.getPostCode());
        return addressRepository.save(existingAddress);
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    /*
     * Customer Address Actions
     */

    // Show all the addresses in user
    public Optional<CustomerAddress> getAllsUserAddresss(Integer userId) {
        return addressRepository.findByUserId(userId);
    }

    // Show the address by id of user
    public CustomerAddress getUserAddressById(Integer userId) {
        return addressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    // Add a new address to that user
    public CustomerAddress createUserAddress(Integer userId, @NotNull CustomerAddress address) {
        CustomerAddress addressExist = addressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        addressExist.setBname(address.getBname());
        addressExist.setStreet(address.getStreet());
        addressExist.setSuburb(address.getSuburb());
        addressExist.setState(address.getState());
        addressExist.setPostCode(address.getPostCode());
        return addressRepository.save(address);
    }

    // Update the address by id of user
    public CustomerAddress updateUserAddress(Integer userId, @NotNull CustomerAddress address) {
        CustomerAddress existingAddress = addressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        existingAddress.setBname(address.getBname());
        existingAddress.setStreet(address.getStreet());
        existingAddress.setSuburb(address.getSuburb());
        existingAddress.setState(address.getState());
        existingAddress.setPostCode(address.getPostCode());
        return addressRepository.save(existingAddress);
    }

    // Delete the address by id of user
    public void deleteUserAddress(Integer userId) {
        addressRepository.deleteByUserId(userId);
    }

    /*
     * Restaurant Address Actions
     */
    public RestaurantAddress getRestaurantAddressById(Integer restaurantId) {
        return addressRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }



}
