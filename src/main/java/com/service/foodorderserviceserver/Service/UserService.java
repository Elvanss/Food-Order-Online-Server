package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Address;
import com.service.foodorderserviceserver.Entity.Type.MembershipType;
import com.service.foodorderserviceserver.Entity.Type.Roles;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.Address.AddressRepository;
import com.service.foodorderserviceserver.Repository.User.UserRepository;

import jakarta.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

//    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Integer userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
    }

    public User save(User user, Roles role) {
        user.setUserName(user.getUserName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setType(role);
        return this.userRepository.save(user);
    }

    /**
     * We are not using this update to change user password.
     *
     * @param userId
     * @param update
     * @return
     */

    public User update(Integer userId, User update) {
        User oldUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
        oldUser.setUserName(update.getUserName());
        oldUser.setEmail(update.getEmail());
        oldUser.setPassword(update.getPassword());
        return this.userRepository.save(oldUser);
    }

    public void delete(Integer userId) {
        this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
        this.userRepository.deleteById(userId);
    }

    @Transactional 
    public void assignAddress(Integer userId, Integer addressId) {
        Address addressToBeAssigned = this.addressRepository.findById(addressId)
                .orElseThrow(() -> new ObjectNotFoundException("address not found!", addressId));
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));

        // if (addressToBeAssigned.getUser() != null) {
        //     addressToBeAssigned.getUser().removeArtifact(addressToBeAssigned);
        // }
        user.addAddress(addressToBeAssigned);
    }


}