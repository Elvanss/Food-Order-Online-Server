package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Address;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.Type.ItemCategory;
import com.service.foodorderserviceserver.Repository.AddressRepository;
import com.service.foodorderserviceserver.Repository.RestaurantRepository;
import com.service.foodorderserviceserver.System.exception.CustomObjectNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, AddressRepository addressRepository) {
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
    }
    // Get all restaurants
    @Transactional
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Get restaurant by ID
    @Transactional
    public Restaurant getRestaurantById(int restaurantId) {
        return restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
    }

    // Add a new restaurant
    @Transactional
    public Restaurant register(Restaurant restaurant) {
        restaurant.setRestaurantName(restaurant.getRestaurantName());
        restaurant.setEmail(restaurant.getEmail());
        restaurant.setPassword(restaurant.getPassword());
        restaurant.setPhone(restaurant.getPhone());
        restaurant.setCuisine(restaurant.getCuisine());
        restaurant.setOpenTime(restaurant.getOpenTime());
        restaurant.setCloseTime(restaurant.getCloseTime());
        restaurant.setOpened(restaurant.isOpened());
        restaurant.setDescription(restaurant.getDescription());
        return restaurantRepository.save(restaurant);
    }

    public Restaurant login(Restaurant restaurant) {
        Restaurant foundedRestaurant = this.restaurantRepository.findByEmail(restaurant.getEmail())
                .orElseThrow(() -> new CustomObjectNotFoundException("Restaurant not found!", restaurant.getRestaurantName()));
        if (foundedRestaurant.getPassword().equals(restaurant.getPassword())) {
            return foundedRestaurant;
        } else {
            throw new RuntimeException("Password is incorrect");
        }
    }

    public List<Restaurant> findAllByItemType(ItemCategory itemCategory) {
        return this.restaurantRepository.findByItemType(itemCategory);
    }

    // Update an existing restaurant
    @Transactional
    public Restaurant updateRestaurant(int restaurantId, Restaurant restaurantDTO) {
        Restaurant existingRestaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));

        existingRestaurant.setRestaurantName(restaurantDTO.getRestaurantName());
        existingRestaurant.setEmail(restaurantDTO.getEmail());
        existingRestaurant.setPhone(restaurantDTO.getPhone());
        existingRestaurant.setCuisine(restaurantDTO.getCuisine());
        existingRestaurant.setOpenTime(restaurantDTO.getOpenTime());
        existingRestaurant.setCloseTime(restaurantDTO.getCloseTime());
        existingRestaurant.setOpened(restaurantDTO.isOpened());
        existingRestaurant.setDescription(restaurantDTO.getDescription());
        restaurantRepository.save(existingRestaurant);
        return existingRestaurant;
    }

    // Delete a restaurant
    @Transactional
    public void deleteRestaurant(int restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        restaurant.removeAllItems();
        restaurantRepository.delete(restaurant);
    }

    // Open a restaurant
    @Transactional
    public void openRestaurant(int restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        restaurant.openRestaurant();
        restaurantRepository.save(restaurant);
    }

    // Close a restaurant
    @Transactional
    public void closeRestaurant(int restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        restaurant.closeRestaurant();
        restaurantRepository.save(restaurant);
    }

    // Update the open status of all restaurants
    @Transactional
    @Scheduled(fixedRate = 60000)
    public void updateRestaurantOpenStatus() {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        for (Restaurant restaurant : allRestaurants) {
            Time now = new Time(System.currentTimeMillis());
            if (now.after(restaurant.getOpenTime()) && now.before(restaurant.getCloseTime())) {
                if (!restaurant.isOpened()) {
                    openRestaurant(restaurant.getId());
                }
            } else {
                if (restaurant.isOpened()) {
                    closeRestaurant(restaurant.getId());
                }
            }
        }
    }

    public List<Restaurant> getRestaurantByCuisine(String cuisine) {
        return restaurantRepository.findByCuisine(cuisine);
    }

    @Transactional
    public void assignAddress(Integer restaurantId, Integer addressId) {
        Address addressToBeAssigned = this.addressRepository.findById(addressId)
                .orElseThrow(() -> new ObjectNotFoundException(addressId, "Address not found"));
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));

        if (addressToBeAssigned.getRestaurant() != null) {
            throw new RuntimeException("Address is already assigned to a restaurant");
        }
        restaurant.addAddress(addressToBeAssigned);
    }

    // Searching items in a restaurant
    public List<Restaurant> searchItems(String itemName) {
        return restaurantRepository.findByItemName(itemName);
    }


}

