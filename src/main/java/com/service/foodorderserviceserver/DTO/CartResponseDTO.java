package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.DTO.User.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class CartResponseDTO {
    private Integer id;
    private UserDTO userId;
    private Date createdDate;
    private List<CartLineItemDTO> cartItems; // Store all items in cart
}
