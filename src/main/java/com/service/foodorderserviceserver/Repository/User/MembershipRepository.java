package com.service.foodorderserviceserver.Repository.User;

import com.service.foodorderserviceserver.Entity.User.Membership;
import com.service.foodorderserviceserver.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    // Find the membership user by user id
    @Query("SELECT m FROM Membership m WHERE m.membershipId = ?1")
    boolean existsByMemId(int userId);

    // Find the membership by user and expiration date after
    List<Membership> findByUserAndExpirationDateAfter(Optional<User> user, Date currentDate);
}
