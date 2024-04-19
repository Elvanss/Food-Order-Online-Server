package com.service.foodorderserviceserver.DTO.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressDTO {
    private Integer id;
    private String buildingName;
    private String street;
    private String suburb;
    private String state;
    private String postalCode;
    private Integer userId;
}