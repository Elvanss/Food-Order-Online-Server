package com.service.foodorderserviceserver.DTO.Address;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
public class AddressDTO {
    private Integer id;
    private String bname;
    private String street;
    private String suburb;
    private String state;
    private String postCode;

    public AddressDTO(Integer id, String bname, String street, String suburb, String state, String postCode) {
        this.id = id;
        this.bname = bname;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postCode = postCode;
    }
}


