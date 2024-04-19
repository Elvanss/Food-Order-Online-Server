package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.DTO.User.UserDTO;
import lombok.Data;

import java.sql.Date;

@Data
public class CartDTO {
    private Integer id;
    private UserDTO userId;
    private Date createdDate;
}
