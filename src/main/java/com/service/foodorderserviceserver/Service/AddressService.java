package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Address.Address;
import com.service.foodorderserviceserver.Entity.Address.CustomerAddress;
import com.service.foodorderserviceserver.Entity.Address.RestaurantAddress;
import com.service.foodorderserviceserver.Repository.Address.AddressRepository;
import jakarta.transaction.Transactional;
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

//    public Address updateAddress(Integer id, Address address) {
//        Address existingAddress = addressRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Address not found"));
//        existingAddress.setBname(address.getBname());
//        existingAddress.setStreet(address.getStreet());
//        existingAddress.setSuburb(address.getSuburb());
//        existingAddress.setState(address.getState());
//        existingAddress.setPostCode(address.getPostCode());
//        return addressRepository.save(existingAddress);
//    }

//    public Address updateAddress(Integer id, Address address) {
//        Address existingAddress = addressRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Address not found"));
//
//        if (address.getBname() != null) {
//            existingAddress.setBname(address.getBname());
//        }
//        if (address.getStreet() != null) {
//            existingAddress.setStreet(address.getStreet());
//        }
//        if (address.getSuburb() != null) {
//            existingAddress.setSuburb(address.getSuburb());
//        }
//        if (address.getState() != null) {
//            existingAddress.setState(address.getState());
//        }
//        if (address.getPostCode() != null) {
//            existingAddress.setPostCode(address.getPostCode());
//        }
//
//        return addressRepository.save(existingAddress);
//    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    /*
     * Customer Address Actions
     */

    public CustomerAddress getUserAddressesById(Integer userId, Integer addressId) {
        // Find the existing address
        Optional<CustomerAddress> existingAddressOpt = addressRepository.findByUserId(userId);
        if (existingAddressOpt.isPresent()) {
            CustomerAddress existingAddress = existingAddressOpt.get();
            if (existingAddress.getId().equals(addressId)) {
                return existingAddress;
            } else {
                throw new RuntimeException("Address id does not match the user's address id");
            }
        } else {
            throw new RuntimeException("Address not found for the user");
        }
    }



    // Show all the addresses in userid
    @Transactional
    public Optional<CustomerAddress> getAllsUserAddresses(Integer userId) {
        return addressRepository.findByUserId(userId);
    }

    // Show the address by id of user
    public CustomerAddress getUserAddressById(Integer userId) {
        return addressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    // Add a new address to that user
    public CustomerAddress createUserAddress(Integer userId, CustomerAddress address) {
        CustomerAddress addressExist = addressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        addressExist.setBname(address.getBname());
        addressExist.setStreet(address.getStreet());
        addressExist.setSuburb(address.getSuburb());
        addressExist.setState(address.getState());
        addressExist.setPostCode(address.getPostCode());
//        addressExist.setUser(address.getUser());
        return addressRepository.save(address);
    }

    // Delete the address by id of user
    public void deleteUserAddress(Integer userId, Integer addressId) {
        addressRepository.deleteByUserId(userId, addressId);
    }

    /*
     * Restaurant Address Actions
     */

    // Show the address by id of restaurant
    public RestaurantAddress getRestaurantAddressById(Integer restaurantId) {
        return addressRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    // Add a new address to that restaurant
    public RestaurantAddress createRestaurantAddress(Integer restaurantId, @NotNull RestaurantAddress address) {
        RestaurantAddress addressExist = addressRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        addressExist.setBname(address.getBname());
        addressExist.setStreet(address.getStreet());
        addressExist.setSuburb(address.getSuburb());
        addressExist.setState(address.getState());
        addressExist.setPostCode(address.getPostCode());
        return addressRepository.save(address);
    }

    // Update the address by id of restaurant
    public CustomerAddress updateUserAddress(Integer userId, Integer addressId, CustomerAddress address) {
        // Find the existing address
        Optional<CustomerAddress> existingAddressOpt = addressRepository.findByUserId(userId);
        if (existingAddressOpt.isPresent()) {
            CustomerAddress existingAddress = existingAddressOpt.get();
            if (existingAddress.getId().equals(addressId)) {
                // Update the existing address with the new data
                existingAddress.setBname(address.getBname());
                existingAddress.setStreet(address.getStreet());
                existingAddress.setSuburb(address.getSuburb());
                existingAddress.setState(address.getState());
                existingAddress.setPostCode(address.getPostCode());
                // Save the updated address back to the database
                return addressRepository.save(existingAddress);
            } else {
                throw new RuntimeException("Address id does not match the user's address id");
            }
        } else {
            throw new RuntimeException("Address not found for the user");
        }
    }

    // Delete the address by id of restaurant
    public void deleteRestaurantAddress(Integer restaurantId) {
        addressRepository.deleteByRestaurantId(restaurantId);
    }

    // Update the address by id of restaurant
    public RestaurantAddress updateRestaurantAddress(Integer restaurantId, RestaurantAddress address) {
        // Find the existing address
        Optional<RestaurantAddress> existingAddressOpt = addressRepository.findByRestaurantId(restaurantId);
        if (existingAddressOpt.isPresent()) {
            RestaurantAddress existingAddress = existingAddressOpt.get();
            // Update the existing address with the new data
            existingAddress.setBname(address.getBname());
            existingAddress.setStreet(address.getStreet());
            existingAddress.setSuburb(address.getSuburb());
            existingAddress.setState(address.getState());
            existingAddress.setPostCode(address.getPostCode());
            return addressRepository.save(existingAddress);
        } else {
            throw new RuntimeException("Address not found for the restaurant");
        }
    }
}
