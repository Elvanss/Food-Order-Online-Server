package com.service.foodorderserviceserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemDTO {
    private Integer id;
    private OrderDTO orderId;
    private ItemDTO productId;
    private Integer quantity;
    private Double totalPrice;
}
