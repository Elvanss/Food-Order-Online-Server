package com.service.foodorderserviceserver.Repository;

import com.service.foodorderserviceserver.Entity.Item;
import com.service.foodorderserviceserver.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    // Find the item by restaurant id
    @Query("select i from Item i where i.restaurantId = ?1")
    List<Item> findByRestaurant(Restaurant restaurant);

    // Find the item by id and restaurant id
    @Query("select i from Item i where i.id = ?1 and i.restaurantId = ?2")
    Optional<Item> findByIdAndRestaurantId(int itemId, Restaurant restaurant);
}
