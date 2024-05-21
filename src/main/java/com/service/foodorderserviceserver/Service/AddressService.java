package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Address;
import com.service.foodorderserviceserver.Repository.AddressRepository;
import com.service.foodorderserviceserver.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    // Show all the addresses in the system
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public Address createAddress(Address newAddress) {
        Address address = new Address();
        address.setBname(newAddress.getBname());
        address.setStreet(newAddress.getStreet());
        address.setSuburb(newAddress.getSuburb());
        address.setState(newAddress.getState());
        address.setPostCode(newAddress.getPostCode());
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
}
