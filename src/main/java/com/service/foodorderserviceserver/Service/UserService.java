package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Address;
import com.service.foodorderserviceserver.Entity.Cart;
import com.service.foodorderserviceserver.Entity.Type.Roles;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.Address.AddressRepository;
import com.service.foodorderserviceserver.Repository.CartLineItemRepository;
import com.service.foodorderserviceserver.Repository.CartRepository;
import com.service.foodorderserviceserver.Repository.User.UserRepository;

import com.service.foodorderserviceserver.System.exception.CustomObjectNotFoundException;
import jakarta.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;



    public UserService(UserRepository userRepository,
                       AddressRepository addressRepository,
                       CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;

    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Integer userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
    }

    // register user
    public User register(User newUser, Roles role) {
        if (userRepository.findByUsername(newUser.getUserName()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setUserName(newUser.getUserName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setPhoneNumber(newUser.getPhoneNumber()); // Ensure this line is present
        user.setType(role);
        User userSaved = this.userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(userSaved);
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        return userSaved;
}

    // Login by Username and Password but parameter is User
    public User login(User user) {
        User foundUser = this.userRepository.findByUserName(user.getUserName())
                .orElseThrow(() -> new CustomObjectNotFoundException("user not found!", user.getUserName()));
        if (foundUser.getPassword().equals(user.getPassword())) {
            return foundUser;
        } else {
            throw new RuntimeException("Password is incorrect");
        }
    }

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

         if (addressToBeAssigned.getUser() != null) {
             addressToBeAssigned.getUser().removeAddress(addressToBeAssigned);
         }
        user.addAddress(addressToBeAssigned);
    }

    // login
    public User changePassword(Integer userId, User update) {
        User oldUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
        oldUser.setPassword(update.getPassword());
        return this.userRepository.save(oldUser);
    }


//    // Check Cart of User
//    private Cart getCartByUserId(Integer userId) {
//        Optional<Cart> foundCart = cartRepository.findByUserId(userId);
//        if (foundCart.isEmpty()) {
//            throw new AppException(HttpStatus.NOT_FOUND.value(), "Not found cart in user");
//        }
//        return foundCart.get();
//    }
//
//    private int getQuantityByUserId(Integer userId) {
//        Cart cart= getCartByUserId(userId);
//        List<CartLineItem> cartLineItems = cartLineItemRepository.findAllByCartId(cart.getId());
//        return cartLineItems.stream().mapToInt(CartLineItem::getQuantity).reduce(0, Integer::sum);
//    }



}