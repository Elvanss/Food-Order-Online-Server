package com.service.foodorderserviceserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
