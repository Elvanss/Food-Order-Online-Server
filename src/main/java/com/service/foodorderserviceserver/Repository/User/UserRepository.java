package com.service.foodorderserviceserver.Repository.User;

import com.service.foodorderserviceserver.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find the user by username
    @Query("select u from User u where u.userName = ?1")
    Optional<User> findByUsername(String userName);

    // Find the user by username or email
    @Query("select u from User u where u.userName = ?1 or u.email = ?1")
    Optional<User> findByUsernameOrEmail(String username, String email);

    // Check if the email exists
    @Query("select case when count(u) > 0 then true else false end from User u where u.email = ?1")
    boolean existsByEmail(String email);

    // Check if the username exists
    @Query("select case when count(u) > 0 then true else false end from User u where u.userName = ?1")
    boolean existsByUsername(String userName);
}
