package com.service.foodorderserviceserver.Repository;

import com.service.foodorderserviceserver.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    // Find the cart by user id
    @Query("SELECT c FROM Cart c WHERE c.user.id = ?1")
    Optional<Cart> findByUserId(Integer userId);

}
