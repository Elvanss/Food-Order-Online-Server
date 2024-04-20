package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.Entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDTO {
    private Integer id;
    private CartDTO cartId;
    private ItemDTO variantProductId;
    private int quantity;
    private Double totalPrice;
    private Date addedDate;
    private boolean isDeleted;
}
