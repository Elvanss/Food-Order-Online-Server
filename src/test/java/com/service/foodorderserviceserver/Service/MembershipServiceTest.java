package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.User.Membership;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.User.MembershipRepository;
import com.service.foodorderserviceserver.Repository.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MembershipServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MembershipRepository membershipRepository;

    @InjectMocks
    private MembershipService membershipService;

    @Test
    void testIsMembershipValid() { // Test case 1 (For Month and Annual Membership)
        // Arrange
        String username = "john_doe";
        User user = new User();
        user.setUserName(username);

        Membership validMembership = new Membership();
        validMembership.setUser(user);
        validMembership.setExpirationDate(Date.valueOf(LocalDate.now().plusDays(10)));

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(membershipRepository.findByUserAndExpirationDateAfter(Optional.of(user), Date.valueOf(LocalDate.now())))
                .thenReturn(List.of(validMembership));

        // Act
        boolean isMembershipValid = membershipService.isMembershipValid(username);

        // Assert
        assertTrue(isMembershipValid);
    }

    @Test
    void testIsMembershipInvalid() { // Test case 2 (Check the valid membership for the user)
        // Arrange
        String username = "john_doe";
        User user = new User();
        user.setUserName(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(membershipRepository.findByUserAndExpirationDateAfter(Optional.of(user), Date.valueOf(LocalDate.now())))
                .thenReturn(Collections.emptyList());

        // Act
        boolean isMembershipValid = membershipService.isMembershipValid(username);

        // Assert
        assertFalse(isMembershipValid);
    }
}