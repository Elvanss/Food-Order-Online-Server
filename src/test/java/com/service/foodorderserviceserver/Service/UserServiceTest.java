package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Address;
//import com.service.foodorderserviceserver.Entity.Types.Roles;
import com.service.foodorderserviceserver.Entity.User.User;
//import com.service.foodorderserviceserver.Repository.Address.AddressRepository;
import com.service.foodorderserviceserver.Repository.CartLineItemRepository;
import com.service.foodorderserviceserver.Repository.CartRepository;
import com.service.foodorderserviceserver.Repository.User.UserRepository;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    // @Mock
    // private UserRepository userRepository;

    // @Mock
    // private AddressRepository addressRepository;

    // @Mock
    // private CartRepository cartRepository;

    // @Mock
    // private CartLineItemRepository cartLineItemRepository;

    // private UserService userService;

    // @BeforeEach
    // void setUp() {
    //     MockitoAnnotations.openMocks(this);
    //     userService = new UserService(userRepository, addressRepository, cartRepository, cartLineItemRepository);
    // }

    // @Test
    // void register() {
    //     User user = new User();
    //     user.setUserName("username");
    //     when(userRepository.findByUsername("username")).thenReturn(Optional.empty());
    //     when(userRepository.save(any(User.class))).thenReturn(user);

    //     User registeredUser = userService.register(user, Roles.USER);

    //     assertEquals(user, registeredUser);
    // }

    // @Test
    // void login() {
    //     User user = new User();
    //     user.setUserName("username");
    //     user.setPassword("password");
    //     when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

    //     User loggedInUser = userService.login(user);

    //     assertEquals(user, loggedInUser);
    // }

    // @Test
    // void findById() {
    //     User user = new User();
    //     user.setId(1);
    //     when(userRepository.findById(1)).thenReturn(Optional.of(user));

    //     User foundUser = userService.findById(1);

    //     assertEquals(user, foundUser);
    // }

    // @Test
    // void findByIdNotFound() {
    //     when(userRepository.findById(1)).thenReturn(Optional.empty());

    //     assertThrows(ObjectNotFoundException.class, () -> userService.findById(1));
    // }

    // @Test
    // void findAll() {
    //     userService.findAll();
    //     verify(userRepository, times(1)).findAll();
    // }


    // @Test
    // void loginPasswordIncorrect() {
    //     User user = new User();
    //     user.setUserName("username");
    //     user.setPassword("wrongpassword");
    //     when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

    //     assertThrows(RuntimeException.class, () -> userService.login(user));
    // }

    // @Test
    // void update() {
    //     User user = new User();
    //     user.setId(1);
    //     user.setUserName("username");
    //     when(userRepository.findById(1)).thenReturn(Optional.of(user));
    //     when(userRepository.save(any(User.class))).thenReturn(user);

    //     User updatedUser = userService.update(1, user);

    //     assertEquals(user, updatedUser);
    // }

    // @Test
    // void delete() {
    //     User user = new User();
    //     user.setId(1);
    //     when(userRepository.findById(1)).thenReturn(Optional.of(user));

    //     userService.delete(1);

    //     verify(userRepository, times(1)).deleteById(1);
    // }

    // @Test
    // void assignAddress() {
    //     User user = new User();
    //     user.setId(1);
    //     Address address = new Address();
    //     address.setId(1);
    //     when(userRepository.findById(1)).thenReturn(Optional.of(user));
    //     when(addressRepository.findById(1)).thenReturn(Optional.of(address));

    //     userService.assignAddress(1, 1);

    //     assertTrue(user.getAddresses().contains(address));
    // }

    // @Test
    // void changePassword() {
    //     User user = new User();
    //     user.setId(1);
    //     user.setPassword("oldpassword");
    //     when(userRepository.findById(1)).thenReturn(Optional.of(user));
    //     when(userRepository.save(any(User.class))).thenReturn(user);

    //     User updatedUser = userService.changePassword(1, user);

    //     assertEquals(user, updatedUser);
    // }
}