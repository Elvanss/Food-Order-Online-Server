package com.service.foodorderserviceserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDTO {
    private CartDTO cartId;
    private ItemDTO productId;
    private int quantity;
}
