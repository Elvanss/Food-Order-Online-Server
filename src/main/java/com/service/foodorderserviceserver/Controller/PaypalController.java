package com.service.foodorderserviceserver.Controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.service.foodorderserviceserver.Entity.Order;
import com.service.foodorderserviceserver.Entity.Type.MembershipType;
import com.service.foodorderserviceserver.Entity.Type.OrderStatus;
import com.service.foodorderserviceserver.Service.MembershipService;
import com.service.foodorderserviceserver.Service.OrderService;
import com.service.foodorderserviceserver.Service.Payment.PaypalService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/paypal")
public class PaypalController {

    private final PaypalService paypalService;
    private final MembershipService membershipService;
    private final OrderService orderService;

    @Value("${paypal.success.url}")
    private String success;

    @Value("${paypal.cancel.url}")
    private String cancel;

    @Autowired
    public PaypalController(PaypalService paypalService, MembershipService membershipService, OrderService orderService) {
        this.paypalService = paypalService;
        this.membershipService = membershipService;
        this.orderService = orderService;
    }

    @PostMapping("/create-payment")
    public ResponseEntity<Map<String, Object>> createPayment(@RequestBody Map<String, String> paymentDetails) {
        try {
            String username = paymentDetails.get("username");
            MembershipType membershipType = MembershipType.valueOf(paymentDetails.get("membershipType"));
            double total = membershipType == MembershipType.MONTHLY ? 8.99 : 129.99;

            Payment payment = paypalService.createPayment(total, "AUD", "paypal", "sale", "Membership payment", cancel, success);

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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
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
            return new Result(true, StatusCode.SUCCESS, "Payment successful!");
        } catch (PayPalRESTException e) {
            return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "Payment failed: " + e.getMessage());
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

    @PostMapping("/create-order-payment")
    public ResponseEntity<Map<String, Object>> createOrderPayment(@RequestBody Map<String, String> paymentDetails) {
        try {
            String orderId = paymentDetails.get("orderId");
            double total = Double.parseDouble(paymentDetails.get("total"));

            Map<String, Object> response = paypalService.createOrderPayment(orderId, total);

            return ResponseEntity.ok(response);
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/order-payment/success")
    public Result orderPaymentSuccess(@RequestParam("paymentId") String paymentId,
                                      @RequestParam("PayerID") String payerId,
                                      @RequestParam("orderId") Integer orderId) {
        try {
            paypalService.executePayment(paymentId, payerId);
            Order order = orderService.findOrder(orderId);
            if (order != null) {
                order.setStatus(OrderStatus.PROCESSING);
                orderService.saveOrder(order);
                return new Result(true, StatusCode.SUCCESS, "Order payment successful!");
            } else {
                return new Result(false, StatusCode.NOT_FOUND, "Order not found.");
            }
        } catch (PayPalRESTException e) {
            if (e.getDetails() != null && "PAYMENT_NOT_APPROVED_FOR_EXECUTION".equals(e.getDetails().getName())) {
                return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "Payment was not approved by the payer.");
            }
            return new Result(false, StatusCode.INTERNAL_SERVER_ERROR, "Order payment failed: " + e.getMessage());
        }
    }
}
