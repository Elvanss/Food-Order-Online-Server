package com.service.foodorderserviceserver.Utils;

import com.service.foodorderserviceserver.Entity.Feedback;
import com.service.foodorderserviceserver.Entity.Item;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Service.FeedbackService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortByRating {

        public static Map<Restaurant, Double> sortItemByRating(List<Item> items, FeedbackService feedbackService) {
            HashMap<Restaurant, Double> ItemsForSorting = new HashMap<>();

            for (Item item : items) {
                Restaurant relatedRestaurant = item.getRestaurantId();
                List<Feedback> relatedFeedbacks = feedbackService.getFeedbackByRestaurant(relatedRestaurant);

                double sum = 0;
                double average = 0;
                if (!relatedFeedbacks.isEmpty()) {
                    for (Feedback feedback : relatedFeedbacks) {
                        sum += feedback.getRating().getValue();
                    }
                    average = sum / relatedFeedbacks.size();
                }

                ItemsForSorting.put(relatedRestaurant, average);
            }

            // Convert the HashMap into a stream
            Stream<Map.Entry<Restaurant, Double>> sorted =
                    ItemsForSorting.entrySet().stream()
                            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

            // Put it into a new LinkedHashMap

            return sorted
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue,
                            LinkedHashMap::new
                    ));
        }

    }



