package com.service.foodorderserviceserver.Entity.User;

import com.service.foodorderserviceserver.Entity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first-name")
    private String firstName;

    @Column(name = "last-name")
    private String lastName;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

    public Integer getNumberOfAddress() {
        return this.addresses.size();
    }

    public void addAddress(Address address) {
        address.setUser(this);
        address.setRestaurant(null);
        this.addresses.add(address);
    }

    public void removeAddress(Address addressToBeAssigned) {
        addressToBeAssigned.setUser(null);
        this.addresses.remove(addressToBeAssigned);
    }

}
