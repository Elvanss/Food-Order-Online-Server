package com.service.foodorderserviceserver.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OrderLineItem")
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order-id", referencedColumnName = "id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "item-id", referencedColumnName = "id")
    private Item itemId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

}