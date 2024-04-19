package com.service.foodorderserviceserver.Service;

import java.sql.Date;

import com.service.foodorderserviceserver.Entity.Type.MembershipType;
import com.service.foodorderserviceserver.Entity.User.Membership;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.User.MembershipRepository;
import com.service.foodorderserviceserver.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    public void signUpForMonthlyMembership(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Date startDate = Date.valueOf(LocalDate.now());
        Date expirationDate = Date.valueOf(LocalDate.now().plusMonths(1));
//        Membership membership = new Membership(user, "Monthly", startDate, expirationDate);
        Membership membership = new Membership();
        membership.setUser(user.get());
        membership.setMembershipType(MembershipType.MONTHLY);
        membership.setStartDate(startDate);
        membership.setExpirationDate(expirationDate);


        membershipRepository.save(membership);
    }

    public void signUpForYearlyMembership(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Date startDate = Date.valueOf(LocalDate.now());
        Date expirationDate = Date.valueOf(LocalDate.now().plusYears(1));
//        Membership membership = new Membership(user, "Yearly", startDate, expirationDate);
        Membership membership = new Membership();
        membership.setUser(user.get());
        membership.setMembershipType(MembershipType.ANNUALLY);
        membership.setStartDate(startDate);
        membership.setExpirationDate(expirationDate);
        membershipRepository.save(membership);
    }

    public boolean isMembershipValid(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        Date currentDate = Date.valueOf(LocalDate.now());
        List<Membership> validMemberships = membershipRepository.findByUserAndExpirationDateAfter(user, currentDate);
        return !validMemberships.isEmpty();
    }

    // Other methods for renewing memberships, etc.

}
