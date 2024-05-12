package com.service.foodorderserviceserver.Utils;

import java.util.List;
import java.util.stream.Collectors;

import com.service.foodorderserviceserver.Entity.Address;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.User.User;

public class GeoLocation {
    private static final double EARTH_RADIUS = 6371; // Approximate radius of the Earth in kilometers

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    public static List<RestaurantDistance> findNearestRestaurants(User user, List<Restaurant> restaurants) {
        List<Address> userAddresses = user.getAddresses();
        if (userAddresses.isEmpty()) {
            // User has no addresses, return an empty list
            return List.of();
        }

        Address userAddress = userAddresses.get(0); // Assuming the first address is the primary one
        double userLatitude = userAddress.getLatitude();
        double userLongitude = userAddress.getLongitude();

        return restaurants.stream()
                .filter(r -> r.getAddresses().get(0) != null && r.getAddresses().get(0).getLatitude() != null && r.getAddresses().get(0).getLongitude() != null)
                .map(r -> new RestaurantDistance(r, calculateDistance(userLatitude, userLongitude, r.getAddresses().get(0).getLatitude(), r.getAddresses().get(0).getLongitude())))
                .sorted((rd1, rd2) -> Double.compare(rd1.getDistance(), rd2.getDistance()))
                .collect(Collectors.toList());
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