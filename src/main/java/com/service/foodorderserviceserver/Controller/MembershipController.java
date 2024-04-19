package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.Service.MembershipService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/memberships")
public class MembershipController {
    @Autowired
    private MembershipService membershipService;

    @PostMapping("/monthly/{username}")
    public Result signUpForMonthlyMembership(@PathVariable String username) {
         membershipService.signUpForMonthlyMembership(username);
        return new Result(true, StatusCode.SUCCESS, "Successfully signed up for monthly membership");
    }

    @PostMapping("/yearly/{username}")
    public Result signUpForYearlyMembership(@PathVariable String username) {
        membershipService.signUpForYearlyMembership(username);
        return new Result(true, StatusCode.SUCCESS, "Successfully signed up for yearly membership");
    }

    @GetMapping("/valid/{username}")
    public Result isMembershipValid(@PathVariable String username) {
        membershipService.isMembershipValid(username);
        if (!membershipService.isMembershipValid(username)) {
            return new Result(false, StatusCode.FORBIDDEN, "Membership is not valid");
        }
        return new Result(true, StatusCode.SUCCESS, "Membership is valid");

    }
}
