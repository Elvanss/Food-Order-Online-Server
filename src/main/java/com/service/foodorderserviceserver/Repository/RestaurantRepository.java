package com.service.foodorderserviceserver.Repository;

import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.Type.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    // Find restaurant by email
    @Query("SELECT r FROM Restaurant r WHERE r.email = ?1")
    Optional<Restaurant> findByEmail(String email);

    // Find restaurant by Cuisine
    @Query("SELECT r FROM Restaurant r WHERE r.cuisine = ?1")
    List<Restaurant> findByCuisine(String cuisine);

    // Find restaurant by item name
    @Query("SELECT r FROM Restaurant r JOIN r.itemList i WHERE i.itemName LIKE %?1%")
    List<Restaurant> findByItemName(String itemName);

    @Query("SELECT r FROM Restaurant r JOIN r.itemList i WHERE i.itemCategory = ?1")
    List<Restaurant> findByItemType(ItemCategory itemCategory);
}
