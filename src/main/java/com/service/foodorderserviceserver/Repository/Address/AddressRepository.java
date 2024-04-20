package com.service.foodorderserviceserver.Repository.Address;

import com.service.foodorderserviceserver.Entity.Address.Address;
import com.service.foodorderserviceserver.Entity.Address.CustomerAddress;
import com.service.foodorderserviceserver.Entity.Address.RestaurantAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT a FROM CustomerAddress a WHERE a.user.id = ?1") // Find all the addresses of a user
    Optional<CustomerAddress> findByUserId(Integer userId); // Find all the addresses of a user

    @Modifying
    @Transactional
    @Query("DELETE FROM CustomerAddress a WHERE a.user.id = ?1 AND a.id = ?2")
    void deleteByUserId(Integer userId, Integer addressId);


    @Query("SELECT a FROM RestaurantAddress a WHERE a.id = ?1") // Find all the addresses of a restaurant
    Optional<RestaurantAddress> findByRestaurantId(Integer restaurantId);

    @Query("DELETE FROM RestaurantAddress a WHERE a.id = ?1")
    void deleteByRestaurantId(Integer restaurantId);
}
