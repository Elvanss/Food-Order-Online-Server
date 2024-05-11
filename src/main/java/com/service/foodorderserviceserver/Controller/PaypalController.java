package com.service.foodorderserviceserver.Controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.service.foodorderserviceserver.Entity.Type.MembershipType;
import com.service.foodorderserviceserver.Service.MembershipService;
import com.service.foodorderserviceserver.Service.Payment.PaypalService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/paypal")
public class PaypalController {

    private final PaypalService paypalService;
    private final MembershipService membershipService;

    @Value("${paypal.success.url}")
    private String success;

    @Value("${paypal.cancel.url}")
    private String cancel;

    public PaypalController(PaypalService paypalService, MembershipService membershipService) {
        this.paypalService = paypalService;
        this.membershipService = membershipService;
    }

    @PostMapping("/create-payment")
    public ResponseEntity<Map<String, Object>> createPayment(@RequestBody Map<String, String> paymentDetails) {
        try {
            String username = paymentDetails.get("username");
            MembershipType membershipType = MembershipType.valueOf(paymentDetails.get("membershipType"));
            double total = membershipType == MembershipType.MONTHLY ? 8.99 : 129.99;

            Payment payment = paypalService.createPayment(
                    total,
                    "AUD",
                    "paypal",
                    "sale",
                    "Membership payment",
                    cancel,
                    success
            );

            Map<String, Object> response = new HashMap<>();
            response.put("id", payment.getId());
            response.put("username", username);
            response.put("membershipType", membershipType);

            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    response.put("redirectUrl", link.getHref());
                }
            }

            return ResponseEntity.ok(response);
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/membership/success")
    public Result paymentSuccess(@RequestParam("paymentId") String paymentId,
                                                 @RequestParam("PayerID") String payerId,
                                                 @RequestParam("username") String username,
                                                 @RequestParam("membershipType") MembershipType membershipType) {
        try {
            paypalService.executePayment(paymentId, payerId);
            createMembership(username, membershipType);
            return new Result(true,  StatusCode.SUCCESS,"Payment successful!");
        } catch (PayPalRESTException e) {
            return new Result(true, StatusCode.INTERNAL_SERVER_ERROR,"Payment failed.");
        }
    }

    @GetMapping("/membership/cancel")
    public ResponseEntity<String> paymentCancel() {
        return ResponseEntity.ok("Payment cancelled.");
    }

    @Transactional
    public void createMembership(String username, MembershipType membershipType) {
        if (membershipType == MembershipType.MONTHLY) {
            membershipService.signUpForMonthlyMembership(username);
        } else if (membershipType == MembershipType.ANNUALLY) {
            membershipService.signUpForYearlyMembership(username);
        }
    }

    //    @GetMapping("/membership/success")
//    public ResponseEntity<String> paymentSuccess(@RequestParam String paymentId,
//                                                 @RequestParam String PayerID,
//                                                 @RequestParam String username) {
//        if (PayerID != null) {
//            try {
//                paypalService.executePayment(paymentId, PayerID);
//                signUpForYearlyMembership(username); // default to yearly membership
//                return ResponseEntity.ok("Payment successful!");
//            } catch (PayPalRESTException e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed.");
//            }
//        } else {
//            return ResponseEntity.ok("Payment cancelled.");
//        }
//    }

//    @RequestMapping(value = "/payment/success", method = {RequestMethod.POST, RequestMethod.GET})
//    public Result paymentSuccess(
//            @RequestParam("paymentId") String paymentId,
//            @RequestParam("PayerID") String payerId,
//            @RequestParam("username") String username,
//            @RequestParam("membershipType") MembershipType membershipType
//    ) {
//        if (payerId == null) {
//            return new Result(false, StatusCode.NOT_FOUND, "Payment cancelled.");
//        }
//        try {
//            Payment payment = paypalService.executePayment(paymentId, payerId);
//            if (payment.getState().equals("approved")) {
//                if (membershipType == MembershipType.MONTHLY) {
//                    membershipService.signUpForMonthlyMembership(username);
//                } else if (membershipType == MembershipType.ANNUALLY) {
//                    membershipService.signUpForYearlyMembership(username);
//                } else {
//                    log.error("Invalid membership type.");
//                }
//                return new Result(true, StatusCode.SUCCESS, "Payment successful!");
//            }
//        } catch (PayPalRESTException e) {
//            log.error("Error occurred:: ", e);
//        }
//        return new Result(false, StatusCode.NOT_FOUND, "Payment failed.");
//    }
}
