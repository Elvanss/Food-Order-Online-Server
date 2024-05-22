package com.service.foodorderserviceserver.Utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.service.foodorderserviceserver.Entity.Address;
import com.service.foodorderserviceserver.Entity.Item;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.Type.CuisineType;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.RestaurantRepository;
import com.service.foodorderserviceserver.Service.RestaurantService;
import org.springframework.stereotype.Component;

@Component
public class GeoLocation {
    private static final double EARTH_RADIUS = 6371; // Approximate radius of the Earth in kilometers
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    public GeoLocation(RestaurantRepository restaurantRepository,
                       RestaurantService restaurantService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
    }

    // Bubble sort from lowest to highest (restaurant distance)
    public static List<RestaurantDistance> bubbleSort(List<RestaurantDistance> restaurants) {
        int n = restaurants.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (restaurants.get(j).getDistance() > restaurants.get(j + 1).getDistance()) {
                    RestaurantDistance temp = restaurants.get(j);
                    restaurants.set(j, restaurants.get(j + 1));
                    restaurants.set(j + 1, temp);
                }
            }
        }
        return restaurants;
    }

    // Bubble sort from highest to lowest (restaurant distance)
    public static List<RestaurantDistance> bubbleSortReverse(List<RestaurantDistance> restaurants) {
        int n = restaurants.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (restaurants.get(j).getDistance() < restaurants.get(j + 1).getDistance()) {
                    RestaurantDistance temp = restaurants.get(j);
                    restaurants.set(j, restaurants.get(j + 1));
                    restaurants.set(j + 1, temp);
                }
            }
        }
        return restaurants;
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    /**
     * Find the nearest restaurants to the user
     *
     * @param user        The user
     * @param restaurants The list of restaurants
     * @return The list of nearest restaurants
     */

    // Find the nearest restaurants to the user (All restaurants) - LANDING PAGE
    public static List<RestaurantDistance> findNearestRestaurants(User user, List<Restaurant> restaurants) {
        List<Address> userAddresses = user.getAddresses();
        if (userAddresses.isEmpty()) {
            return List.of();
        }

        Address userAddress = userAddresses.get(0); // Assuming the first address is the primary one
        double userLatitude = userAddress.getLatitude();
        double userLongitude = userAddress.getLongitude();

        return restaurants.stream()
                .filter(r -> r.getAddresses().get(0) != null && r.getAddresses().get(0).getLatitude() != null && r.getAddresses().get(0).getLongitude() != null)
                .map(r -> new RestaurantDistance(r, calculateDistance(userLatitude, userLongitude, r.getAddresses().get(0).getLatitude(), r.getAddresses().get(0).getLongitude())))
                .sorted(Comparator.comparingDouble(RestaurantDistance::getDistance))
                .collect(Collectors.toList());
    }

    // Find the nearest restaurants to the user by cuisine (type of cuisine) - LANDING PAGE
    public static List<RestaurantDistance> getNearestRestaurantByCuisine(User user, List<Restaurant> restaurants, CuisineType cuisine) {
        List<Address> userAddresses = user.getAddresses();
        if (userAddresses.isEmpty()) {
            return List.of();
        }

        Address userAddress = userAddresses.get(0);
        double userLatitude = userAddress.getLatitude();
        double userLongitude = userAddress.getLongitude();

        return restaurants.stream()
                .filter(r -> r.getAddresses().get(0) != null && r.getAddresses().get(0).getLatitude() != null && r.getAddresses().get(0).getLongitude() != null)
                .filter(r -> r.getCuisine().equals(cuisine))
                .map(r -> new RestaurantDistance(r, calculateDistance(userLatitude, userLongitude, r.getAddresses().get(0).getLatitude(), r.getAddresses().get(0).getLongitude())))
                .sorted(Comparator.comparingDouble(RestaurantDistance::getDistance))
                .collect(Collectors.toList());
    }


    // Filter the restaurants by nearest location from lowest to highest (SEARCHING)
    public List<RestaurantDistance> filterOfSearchingNearestRestaurantLowToHigh(User user, String item) {
        List<Restaurant> restaurantsInSearch = restaurantService.searchItems(item);
        List<Address> userAddresses = user.getAddresses();
        if (userAddresses.isEmpty()) {
            return List.of();
        }

        Address userAddress = userAddresses.get(0); // Take the primary one
        double userLatitude = userAddress.getLatitude();
        double userLongitude = userAddress.getLongitude();

        // Filter the restaurants by nearest location from lowest to highest
        List<RestaurantDistance> nearestRestaurants = restaurantsInSearch.stream()
                .filter(r -> r.getAddresses().get(0) != null && r.getAddresses().get(0).getLatitude() != null && r.getAddresses().get(0).getLongitude() != null)
                .map(r -> new RestaurantDistance(r, calculateDistance(userLatitude, userLongitude, r.getAddresses().get(0).getLatitude(), r.getAddresses().get(0).getLongitude())))
                .sorted(Comparator.comparingDouble(RestaurantDistance::getDistance))
                .collect(Collectors.toList());
        return nearestRestaurants;
    }

    // Filter the restaurants by nearest location from highest to lowest (SEARCHING)
    public List<RestaurantDistance> filterOfSearchingNearestRestaurantHighToLow(User user, String item) {
        List<Restaurant> restaurantsInSearch = restaurantService.searchItems(item);
        List<Address> userAddresses = user.getAddresses();
        if (userAddresses.isEmpty()) {
            return List.of();
        }

        Address userAddress = userAddresses.get(0); // Take the primary one
        double userLatitude = userAddress.getLatitude();
        double userLongitude = userAddress.getLongitude();

        // Filter the restaurants by nearest location from highest to lowest
        List<RestaurantDistance> nearestRestaurants = restaurantsInSearch.stream()
                .filter(r -> r.getAddresses().get(0) != null && r.getAddresses().get(0).getLatitude() != null && r.getAddresses().get(0).getLongitude() != null)
                .map(r -> new RestaurantDistance(r, calculateDistance(userLatitude, userLongitude, r.getAddresses().get(0).getLatitude(), r.getAddresses().get(0).getLongitude())))
                .sorted(Comparator.comparingDouble(RestaurantDistance::getDistance).reversed())
                .collect(Collectors.toList());
        return nearestRestaurants;
    }


    public static class RestaurantDistance {
        private final Restaurant restaurant;
        private final double distance;

        RestaurantDistance(Restaurant restaurant, double distance) {
            this.restaurant = restaurant;
            this.distance = distance;
        }

        public Restaurant getRestaurant() {
            return restaurant;
        }

        public double getDistance() {
            return distance;
        }
    }
}
