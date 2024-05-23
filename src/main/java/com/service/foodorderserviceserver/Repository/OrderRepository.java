package com.service.foodorderserviceserver.Repository;

import com.service.foodorderserviceserver.Entity.Order;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.restaurant = ?1")
    List<Order> findAllOrderByUserAndRestaurant(Restaurant restaurant);

    @Query("SELECT o FROM Order o WHERE o.user = ?1")
    List<Order> findAllOrderByUser(User user);
}
