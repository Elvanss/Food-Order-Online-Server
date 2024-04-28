package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.DTO.User.UserDTO;
import com.service.foodorderserviceserver.Entity.User.User;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CartDTO {
    private Integer id;
    private Double totalPrice;
    private UserDTO userId;
    private Date createdDate;
}
