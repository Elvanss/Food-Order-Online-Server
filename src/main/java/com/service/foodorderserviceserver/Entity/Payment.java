package com.service.foodorderserviceserver.Entity;

import com.service.foodorderserviceserver.Entity.Type.PaymentMethod;
import com.service.foodorderserviceserver.Entity.Type.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String payerId;

    private String payeeId;

    private String payerName;

    private String payeeName;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    private long amount;

    private LocalDateTime paymentDateTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    private Order order;
}