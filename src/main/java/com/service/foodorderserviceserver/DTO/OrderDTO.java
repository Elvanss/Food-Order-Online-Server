package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.Entity.Type.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Integer id;
    private UserDTO user;
    private RestaurantDTO restaurant;
    private AddressDTO address;
    private Integer numberOfOrderItems;
    private Double totalPrice;
    private LocalDateTime orderDate;
    private OrderStatus status;
}
