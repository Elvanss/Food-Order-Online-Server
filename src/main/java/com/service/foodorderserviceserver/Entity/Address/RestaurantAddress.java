package com.service.foodorderserviceserver.Entity.Address;

import com.fasterxml.jackson.annotation.*;
import com.service.foodorderserviceserver.DTO.RestaurantDTO;
import com.service.foodorderserviceserver.Entity.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("RESTAURANT")
@Table(name = "RestaurantAddress")
public class RestaurantAddress extends Address {

    public RestaurantAddress(Integer id, String bname, String street, String suburb, String state, String postCode) {
        super(id, bname, street, suburb, state, postCode);
    }
}

