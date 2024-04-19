package com.service.foodorderserviceserver.Entity.Address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Address")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "address_type", discriminatorType = DiscriminatorType.STRING)
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




}