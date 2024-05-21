package com.service.foodorderserviceserver.Service.Payment;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalService {

    private final APIContext apiContext;

    public PaypalService(APIContext apiContext) {
        this.apiContext = apiContext;
    }

    public Payment createPayment(
            Double total, // total amount of the payment
            String currency, //  currency of the payment
            String method, // payment method
            String intent, // payment intent
            String description, // description of the payment
            String cancelUrl, // cancel url
            String successUrl // success url
    )throws PayPalRESTException {
// create an amount object
        Amount amount = new Amount();
        amount.setCurrency(currency); // Only AUD supported
        amount.setTotal(String.format( "%.2f", total));

        // create a transaction object
        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        //  add transaction to a list
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        // create a payer object
        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        // create a payment object
        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        // add redirect urls to the payment
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        // set redirect urls to the payment
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }
}
