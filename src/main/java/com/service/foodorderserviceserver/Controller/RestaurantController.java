package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.RestaurantDTO;
import com.service.foodorderserviceserver.DTO.RestaurantRatingDTO;
import com.service.foodorderserviceserver.Entity.Item;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.Type.CuisineType;
import com.service.foodorderserviceserver.Entity.Type.ItemCategory;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Mapper.RestaurantMapper;
import com.service.foodorderserviceserver.Service.FeedbackService;
import com.service.foodorderserviceserver.Service.ItemService;
import com.service.foodorderserviceserver.Service.RestaurantService;
import com.service.foodorderserviceserver.Service.UserService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import com.service.foodorderserviceserver.Utils.GeoLocation;

import com.service.foodorderserviceserver.Utils.SortByRating;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/restaurants")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final ItemService itemService;
    private final FeedbackService feedbackService;
    private final UserService userService;
    private final GeoLocation geoLocation;

    public RestaurantController(RestaurantService restaurantService,
                                RestaurantMapper restaurantMapper,
                                ItemService itemService,
                                FeedbackService feedbackService,
                                UserService userService, GeoLocation geoLocation) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
        this.itemService = itemService;
        this.feedbackService = feedbackService;
        this.userService = userService;
        this.geoLocation = geoLocation;
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

    @GetMapping("/search")
    public Result searchRestaurant(@RequestParam("item") String item) {
        List<Restaurant> restaurants = restaurantService.searchItems(item);
        List<RestaurantDTO> restaurantDTOS = restaurants.stream()
                .map(this.restaurantMapper::convertToDto)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Success", restaurantDTOS);
    }

    @GetMapping("itemCategory/{category}")
    public Result getRestaurantByItemCategory(@PathVariable ItemCategory category) {
        List<Restaurant> restaurants = restaurantService.findAllByItemType(category);
        List<RestaurantDTO> restaurantDTOS = restaurants.stream()
                .map(this.restaurantMapper::convertToDto)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Success", restaurantDTOS);
    }

    /**
     * Get the nearest restaurants functions
     */

    // Get the nearest restaurants Filters by the user (LANDING PAGE)
   @GetMapping("/nearest")
    public Result getNearestRestaurants (@RequestParam("userId") Integer userId) {
        User user = userService.findById(userId); // Get the user
        List<Restaurant> allRestaurants = restaurantService.getAllRestaurants(); // Get all restaurants
        List<GeoLocation.RestaurantDistance> nearestRestaurants = geoLocation.findNearestRestaurants(user, allRestaurants); // Find the nearest restaurants
        List<RestaurantDTO> nearestRestaurantDTOs = nearestRestaurants.stream()
                .map(restaurantDistance -> restaurantMapper.convertToDtoByDistance(restaurantDistance))
                .toList();
        
        return new Result(true, StatusCode.SUCCESS, "Success", nearestRestaurantDTOs);
    }

    // Get the nearest restaurants by cuisine Filters by the user (LANDING PAGE)
    @GetMapping("/nearest/{cuisine}")
    public Result getNearestRestaurantsByCuisine(@RequestParam("userId") Integer userId,
                                                 @PathVariable("cuisine") CuisineType cuisine) {
        User user = userService.findById(userId);
        List<Restaurant> allRestaurants = restaurantService.getAllRestaurants();
        List<GeoLocation.RestaurantDistance> nearestRestaurants = geoLocation.getNearestRestaurantByCuisine(user, allRestaurants, cuisine);
        List<RestaurantDTO> nearestRestaurantDTOs = nearestRestaurants.stream()
                .map(restaurantDistance -> restaurantMapper.convertToDtoByDistance(restaurantDistance))
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Success", nearestRestaurantDTOs);
    }

    // Get the nearest restaurants by cuisine Filters by the user Low to High (SEARCHING)
    @GetMapping("/nearest/lowtohigh")
    public Result searchingNearestRestaurantLowToHigh(@RequestParam("userId") Integer userId,
                                                      @RequestParam("item") String item) {
        User user = userService.findById(userId);
        List<GeoLocation.RestaurantDistance> nearestRestaurants = geoLocation.filterOfSearchingNearestRestaurantLowToHigh(user, item);
        List<RestaurantDTO> nearestRestaurantDTOs = nearestRestaurants.stream()
                .map(restaurantDistance -> restaurantMapper.convertToDtoByDistance(restaurantDistance))
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Success", nearestRestaurantDTOs);
    }

    @GetMapping("/nearest/hightolow")
    public Result searchingNearestRestaurantHighToLow(@RequestParam("userId") Integer userId,
                                                      @RequestParam("item") String item) {
        User user = userService.findById(userId);
        List<GeoLocation.RestaurantDistance> nearestRestaurants = geoLocation.filterOfSearchingNearestRestaurantHighToLow(user, item);
        List<RestaurantDTO> nearestRestaurantDTOs = nearestRestaurants.stream()
                .map(restaurantDistance -> restaurantMapper.convertToDtoByDistance(restaurantDistance))
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Success", nearestRestaurantDTOs);
    }

    // Rating
    @GetMapping("/findByRating/{itemName}")
    public Result findItemByRating(@PathVariable String itemName) {
        List<Item> relatedItems = itemService.findRelatedItemByRating(itemName);
        Map<Restaurant, Double> relatedRestaurants = SortByRating.sortItemByRating(relatedItems, feedbackService);

        // Convert Restaurant entities to RestaurantDTO objects and create RestaurantRatingDTO objects
        List<RestaurantRatingDTO> relatedRestaurantRatings = new ArrayList<>();
        for (Map.Entry<Restaurant, Double> entry : relatedRestaurants.entrySet()) {
            RestaurantDTO dto = restaurantMapper.convertToDto(entry.getKey());
            RestaurantRatingDTO restaurantRating = new RestaurantRatingDTO();
            restaurantRating.setRestaurant(dto);
            restaurantRating.setAverageRating(entry.getValue());
            relatedRestaurantRatings.add(restaurantRating);
        }

        return new Result(true, StatusCode.SUCCESS, "Success", relatedRestaurantRatings);
    }






}
