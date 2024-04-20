package com.service.foodorderserviceserver.Entity.Address;

import com.service.foodorderserviceserver.Entity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@DiscriminatorValue("CUSTOMER_ADDRESS")
@Table(name = "CustomerAddress")
public class CustomerAddress extends Address {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public CustomerAddress(Integer id, String bname, String street, String suburb, String state, String postCode, User user) {
        super(id, bname, street, suburb, state, postCode);
        this.user = user;

    }
}
