package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.User.UserDTO;
import com.service.foodorderserviceserver.Entity.Type.Roles;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Mapper.User.UserMapper;
import com.service.foodorderserviceserver.Service.UserService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper; // Convert user to userDto.


    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    //Login user with username and password
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDto) {
        User user = this.userMapper.convertToEntity(userDto);
        User user1 = this.userService.login(user);
        UserDTO userDto1 = this.userMapper.convertToDto(user1);
        return new Result(true, StatusCode.SUCCESS, "Login Success", userDto1);
    }

    // Register a new user.
    @PostMapping("/register")
    public Result register(@RequestBody User newUser, @RequestParam(name = "role", required=false) Roles role) {
        User savedUser = this.userService.register(newUser, role);
        UserDTO savedUserDto = this.userMapper.convertToDto(savedUser);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedUserDto);
    }

    // Change password.
    @PutMapping("/changePassword/{userId}")
    public Result changePassword(@PathVariable Integer userId, @RequestBody UserDTO userDto) {
        User update = this.userMapper.convertToEntity(userDto);
        User updatedUser = this.userService.changePassword(userId, update);
        updatedUser.setPassword(updatedUser.getPassword());
        UserDTO updatedUserDto = this.userMapper.convertToDto(updatedUser);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedUserDto);
    }

    // Get all users.
    @GetMapping
    public Result findAllUsers() {
        List<User> foundUser = this.userService.findAll();

        List<UserDTO> userDtos = foundUser.stream() // Convert the list to a stream.
                .map(this.userMapper::convertToDto) // Convert each item to UserDto.
                .collect(Collectors.toList()); // Collect the stream to a list.

        // Note that UserDto does not contain password field.
        return new Result(true, StatusCode.SUCCESS, "Find All Success", userDtos);
    }

    // Get a user by id.
    @GetMapping("/{userId}")
    public Result findUserById(@PathVariable Integer userId) {
        User foundUser = this.userService.findById(userId);
        UserDTO userDto = this.userMapper.convertToDto(foundUser);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", userDto);
    }

    // We are not using this to update password, need another changePassword method in this class.
    @PutMapping("/update/{userId}")
    public Result updateUser(@PathVariable Integer userId, @RequestBody UserDTO userDto) {
        User update = this.userMapper.convertToEntity(userDto);
        User updatedUser = this.userService.update(userId, update);
        updatedUser.setPassword(updatedUser.getPassword());
        UserDTO updatedUserDto = this.userMapper.convertToDto(updatedUser);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedUserDto);
    }

    // Change password.
    @DeleteMapping("/delete/{userId}")
    public Result deleteUser(@PathVariable Integer userId) {
        this.userService.delete(userId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

    // Assign an address to a user.
    @PostMapping("/{userId}/address/{addressId}")
    public Result assignAddress(@PathVariable Integer userId, @PathVariable Integer addressId) {
        this.userService.assignAddress(userId, addressId);
        return new Result(true, StatusCode.SUCCESS, "Assign Address Success", addressId);
    }
}