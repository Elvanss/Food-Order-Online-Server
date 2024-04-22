package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.RestaurantDTO;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Mapper.RestaurantMapper;
import com.service.foodorderserviceserver.Service.RestaurantService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/restaurants")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    public RestaurantController(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
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

    //Update Restaurant
    @PutMapping("/{restaurantId}")
     public Result updateRestaurant(@PathVariable Integer restaurantId, @RequestBody RestaurantDTO restaurantDTO) {
         Restaurant update = restaurantMapper.convertToEntity(restaurantDTO);
         Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantId, update);
         RestaurantDTO updatedRestaurantDTO = restaurantMapper.convertToDto(updatedRestaurant);
         return new Result(true, StatusCode.SUCCESS, "Success", updatedRestaurantDTO);
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

    @PostMapping
    public Result addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantMapper.convertToEntity(restaurantDTO);
        Restaurant newRestaurant = restaurantService.addRestaurant(restaurant);
        RestaurantDTO newRestaurantDTO = restaurantMapper.convertToDto(newRestaurant);
        return new Result(true, StatusCode.SUCCESS, "Restaurant added successfully", newRestaurantDTO);
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



}
