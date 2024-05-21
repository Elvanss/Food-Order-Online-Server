package com.service.foodorderserviceserver.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class CartResponseDTO {
    private Integer id;
    private Double totalPrice;
    private UserDTO user;
    private List<CartLineItemDTO> cartItems; // Store all items in cart
}
