package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.DTO.User.UserDTO;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CartDTO {
    private Integer id;
    private Double totalPrice;
    private Integer userId;
    private Date createdDate;
}
