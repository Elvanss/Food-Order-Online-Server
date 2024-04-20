package com.service.foodorderserviceserver.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import com.service.foodorderserviceserver.Entity.User.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Address")
public class Address implements Serializable {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bname", columnDefinition = "VARCHAR(255)")
    private String bname;

    @Column(name = "street", columnDefinition = "VARCHAR(255)")
    private String street;

    @Column(name = "suburb", columnDefinition = "VARCHAR(255)")
    private String suburb;

    @Column(name = "state", columnDefinition = "VARCHAR(255)")
    private String state;

    @Column(name = "postCode", columnDefinition = "VARCHAR(255)")
    private String postCode;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "address")
    private Restaurant restaurant;


    public Address(String bname, String street, String suburb, String state, String postCode) {
        this.bname = bname;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postCode = postCode;
    }
}