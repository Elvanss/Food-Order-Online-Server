package com.service.foodorderserviceserver.Repository;

import com.service.foodorderserviceserver.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    // Find restaurant by email
    @Query("SELECT r FROM Restaurant r WHERE r.email = ?1")
    Optional<Restaurant> findByEmail(String email);
}
