package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.User.UserDTO;
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

    // Register a new user.
//    @PostMapping("/register")
//    public Result register(@RequestBody User newUser, @RequestParam(name = "role", required=false) Roles role) {
//        User savedUser = this.userService.save(newUser, role);
//        UserDTO savedUserDto = this.userMapper.convertToDto(savedUser);
//        return new Result(true, StatusCode.SUCCESS, "Add Success", savedUserDto);
//    }

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
}