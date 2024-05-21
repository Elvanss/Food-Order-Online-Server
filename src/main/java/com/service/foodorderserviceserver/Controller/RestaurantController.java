package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.RestaurantDTO;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Mapper.RestaurantMapper;
import com.service.foodorderserviceserver.Service.RestaurantService;
import com.service.foodorderserviceserver.Service.UserService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import com.service.foodorderserviceserver.Utils.GeoLocation;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/restaurants")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final UserService userService;

    public RestaurantController(RestaurantService restaurantService, 
                                RestaurantMapper restaurantMapper, 
                                UserService userService) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
        this.userService = userService;
    }

    // Get all restaurants
     @GetMapping
     public Result getAllRestaurants() {
         List<Restaurant> restaurants = restaurantService.getAllRestaurants();
         List<RestaurantDTO> restaurantDTOS = restaurants.stream() // Convert the list to a stream.
                 .map(this.restaurantMapper::convertToDto) // Convert each item to UserDto.
                 .collect(Collectors.toList());
         return new Result(true, StatusCode.SUCCESS,"Success", restaurantDTOS);
     }

     // Get a restaurant by id
     @GetMapping("/{restaurantId}")
     public Result getRestaurantById(@PathVariable Integer restaurantId) {
         Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
         RestaurantDTO restaurantDTO = restaurantMapper.convertToDto(restaurant);
         return new Result(true, StatusCode.SUCCESS, "Success", restaurantDTO);
     }

    //Register Restaurant
    @PutMapping("/update/{restaurantId}")
     public Result register(@PathVariable Integer restaurantId, @RequestBody RestaurantDTO restaurantDTO) {
         Restaurant update = restaurantMapper.convertToEntity(restaurantDTO);
         Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantId, update);
         RestaurantDTO updatedRestaurantDTO = restaurantMapper.convertToDto(updatedRestaurant);
         return new Result(true, StatusCode.SUCCESS, "Success", updatedRestaurantDTO);
     }

     // Login
    @PostMapping("/login")
    public Result login(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantMapper.convertToEntity(restaurantDTO);
        Restaurant loginRestaurant = restaurantService.login(restaurant);
        RestaurantDTO loginRestaurantDTO = restaurantMapper.convertToDto(loginRestaurant);
        return new Result(true, StatusCode.SUCCESS, "Login successful", loginRestaurantDTO);
    }

    @PostMapping("/register")
    public Result addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantMapper.convertToEntity(restaurantDTO);
        Restaurant newRestaurant = restaurantService.register(restaurant);
        RestaurantDTO newRestaurantDTO = restaurantMapper.convertToDto(newRestaurant);
        return new Result(true, StatusCode.SUCCESS, "Restaurant added successfully", newRestaurantDTO);
    }

    @DeleteMapping("/{restaurantId}")
    public Result deleteRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return new Result(true, StatusCode.SUCCESS, "Restaurant deleted successfully");
    }

    @PutMapping("/{restaurantId}/open")
    public Result openRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.openRestaurant(restaurantId);
        return new Result(true, StatusCode.SUCCESS, "Restaurant opened successfully");
    }

    @PutMapping("/{restaurantId}/close")
    public Result closeRestaurant(@PathVariable Integer restaurantId) {
        restaurantService.closeRestaurant(restaurantId);
        return new Result(true, StatusCode.SUCCESS, "Restaurant closed successfully");
    }


    @GetMapping("/cuisine/{cuisine}")
    public Result getRestaurantByCuisine(@PathVariable String cuisine) {
        List<Restaurant> restaurants = restaurantService.getRestaurantByCuisine(cuisine);
        List<RestaurantDTO> restaurantDTOS = restaurants.stream()
                .map(this.restaurantMapper::convertToDto)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Success", restaurantDTOS);
    }

    @PostMapping("/{restaurantId}/assign/{userId}")
    public Result assignAddress(@PathVariable Integer restaurantId, @PathVariable Integer userId) {
        restaurantService.assignAddress(restaurantId, userId);
        return new Result(true, StatusCode.SUCCESS, "User assigned successfully", userId);
    }

    // Get the nearest restaurants Filters
   @GetMapping("/nearest")
    public Result getNearestRestaurants (@RequestParam("userId") Integer userId) {
        User user = userService.findById(userId); // Get the user
        List<Restaurant> allRestaurants = restaurantService.getAllRestaurants(); // Get all restaurants
        List<GeoLocation.RestaurantDistance> nearestRestaurants = GeoLocation.findNearestRestaurants(user, allRestaurants); // Find the nearest restaurants
        List<RestaurantDTO> nearestRestaurantDTOs = nearestRestaurants.stream()
                .map(restaurantDistance -> restaurantMapper.convertToDto(restaurantDistance.getRestaurant()))
                .toList();
        
        return new Result(true, StatusCode.SUCCESS, "Success", nearestRestaurantDTOs);
    }

    @GetMapping("/nearest/{cuisine}")
    public Result getNearestRestaurantsByCuisine(@RequestParam("userId") Integer userId, @PathVariable String cuisine) {
        User user = userService.findById(userId);
        List<Restaurant> allRestaurants = restaurantService.getRestaurantByCuisine(cuisine);
        List<GeoLocation.RestaurantDistance> nearestRestaurants = GeoLocation.getNearestRestaurantByCuisine(user, allRestaurants, cuisine);
        List<RestaurantDTO> nearestRestaurantDTOs = nearestRestaurants.stream()
                .map(restaurantDistance -> restaurantMapper.convertToDto(restaurantDistance.getRestaurant()))
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Success", nearestRestaurantDTOs);
    }



}
