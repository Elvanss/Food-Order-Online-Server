package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.DTO.User.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Setter
@Getter
public class CartDTO {
    private Integer id;
    private Integer userId;
    private Date createdDate;
}
