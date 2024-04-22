package com.service.foodorderserviceserver.Repository;

import com.service.foodorderserviceserver.Entity.Feedback;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByUser(User user);
    List<Feedback> findByRestaurant(Restaurant restaurant);
}
