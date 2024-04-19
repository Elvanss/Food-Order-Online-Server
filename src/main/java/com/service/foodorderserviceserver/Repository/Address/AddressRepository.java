package com.service.foodorderserviceserver.Repository.Address;

import com.service.foodorderserviceserver.Entity.Address.Address;
import com.service.foodorderserviceserver.Entity.Address.CustomerAddress;
import com.service.foodorderserviceserver.Entity.Address.RestaurantAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT a FROM CustomerAddress a WHERE a.user.id = ?1")
    Optional<CustomerAddress> findByUserId(Integer userId);

    @Query("DELETE FROM CustomerAddress a WHERE a.user.id = ?1")
    void deleteByUserId(Integer userId);

    @Query("SELECT a FROM RestaurantAddress a WHERE a.restaurant.id = ?1")
    Optional<RestaurantAddress> findByRestaurantId(Integer restaurantId);
}
