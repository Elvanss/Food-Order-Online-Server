package com.service.foodorderserviceserver.Entity;


import com.service.foodorderserviceserver.Entity.Type.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`Order`")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private long totalPrice;

    private LocalDateTime orderDateTime;

    private String restaurantName;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    private Cart cart;

    // getters and setters
}