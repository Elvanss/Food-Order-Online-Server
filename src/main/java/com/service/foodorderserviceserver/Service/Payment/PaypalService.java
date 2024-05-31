package com.service.foodorderserviceserver.Service.Payment;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaypalService {

    private final APIContext apiContext;

    @Value("${paypal.successOrder.url}")
    private String successUrl;

    @Value("${paypal.cancelOrder.url}")
    private String cancelUrl;

    @Autowired
    public PaypalService(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    public Payment createPayment(Double total, String currency, String method, String intent, String description,
                                 String cancelUrl, String successUrl) throws PayPalRESTException {
        Amount amount = createAmount(total, currency);
        Transaction transaction = createTransaction(description, amount);
        Payer payer = createPayer(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(Collections.singletonList(transaction));
        payment.setRedirectUrls(createRedirectUrls(cancelUrl, successUrl));

        return payment.create(apiContext);
    }

    private Amount createAmount(Double total, String currency) {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));
        return amount;
    }

    private Transaction createTransaction(String description, Amount amount) {
        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);
        return transaction;
    }

    private Payer createPayer(String method) {
        Payer payer = new Payer();
        payer.setPaymentMethod(method);
        return payer;
    }

    private RedirectUrls createRedirectUrls(String cancelUrl, String successUrl) {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        return redirectUrls;
    }

    public void executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        payment.execute(apiContext, paymentExecution);
    }

    public Map<String, Object> createOrderPayment(String orderId, double total) throws PayPalRESTException {
        Payment payment = createPayment(total, "AUD", "paypal", "sale", "Order payment", cancelUrl, successUrl);

        Map<String, Object> response = new HashMap<>();
        response.put("id", payment.getId());
        response.put("orderId", orderId);

        for (Links link : payment.getLinks()) {
            if (link.getRel().equals("approval_url")) {
                response.put("redirectUrl", link.getHref());
            }
        }

        return response;
    }
}

//@Service
//public class PaypalService {
//
//    private final APIContext apiContext;
//
//    @Value("${paypal.success.url}")
//    private String successUrl;
//
//    @Value("${paypal.cancel.url}")
//    private String cancelUrl;
//
//    public PaypalService(APIContext apiContext) {
//        this.apiContext = apiContext;
//    }
//
//    // method 1: Membership payment
//    public Payment createPayment(
//            Double total, // total amount of the payment
//            String currency, //  currency of the payment
//            String method, // payment method
//            String intent, // payment intent
//            String description, // description of the payment
//            String cancelUrl, // cancel url
//            String successUrl // success url
//    )throws PayPalRESTException {
//// create an amount object
//        Amount amount = new Amount();
//        amount.setCurrency(currency); // Only AUD supported
//        amount.setTotal(String.format( "%.2f", total));
//
//        // create a transaction object
//        Transaction transaction = new Transaction();
//        transaction.setDescription(description);
//        transaction.setAmount(amount);
//
//        //  add transaction to a list
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(transaction);
//
//        // create a payer object
//        Payer payer = new Payer();
//        payer.setPaymentMethod(method);
//
//        // create a payment object
//        Payment payment = new Payment();
//        payment.setIntent(intent);
//        payment.setPayer(payer);
//        payment.setTransactions(transactions);
//
//        // add redirect urls to the payment
//        RedirectUrls redirectUrls = new RedirectUrls();
//        redirectUrls.setCancelUrl(cancelUrl);
//        redirectUrls.setReturnUrl(successUrl);
//
//        // set redirect urls to the payment
//        payment.setRedirectUrls(redirectUrls);
//
//        return payment.create(apiContext);
//    }
//
//    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
//        Payment payment = new Payment();
//        payment.setId(paymentId);
//
//        PaymentExecution paymentExecution = new PaymentExecution();
//        paymentExecution.setPayerId(payerId);
//
//        return payment.execute(apiContext, paymentExecution);
//    }
//
//    // Method 2: Create a payment for an order
//    public Map<String, Object> createOrderPayment(String orderId, double total) throws PayPalRESTException {
//        Payment payment = createPayment(
//                total,
//                "AUD",
//                "paypal",
//                "sale",
//                "Order payment",
//                cancelUrl,
//                successUrl
//        );
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("id", payment.getId());
//        response.put("orderId", orderId);
//
//        for (Links link : payment.getLinks()) {
//            if (link.getRel().equals("approval_url")) {
//                response.put("redirectUrl", link.getHref());
//            }
//        }
//
//        return response;
//    }
//
//
//}
