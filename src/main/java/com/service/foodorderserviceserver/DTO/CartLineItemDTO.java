package com.service.foodorderserviceserver.DTO;

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
    private Integer cartId;
    private Integer variantProductId;
    private int quantity;
    private Double totalPrice;
    private Date addedDate;
    private boolean isDeleted;
}
