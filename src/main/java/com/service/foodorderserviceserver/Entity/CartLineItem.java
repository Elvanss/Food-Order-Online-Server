package com.service.foodorderserviceserver.Entity;

import com.service.foodorderserviceserver.DTO.CartDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cartId;

    @ManyToOne
    @JoinColumn(name = "variant_product_id", referencedColumnName = "id", nullable = false)
    private Item productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "added_date")
    private Date addedDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;
}
