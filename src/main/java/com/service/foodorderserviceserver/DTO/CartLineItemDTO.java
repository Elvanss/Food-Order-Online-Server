package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.Entity.Cart;
import com.service.foodorderserviceserver.Entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartLineItemDTO {
    private Integer id;
    private CartDTO cartId;
    private ItemDTO variantProductId;
    private int quantity;
    private Double totalPrice;
    private boolean isDeleted;
}
