package com.service.foodorderserviceserver.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Random;

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

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;
    
    public Address(String bname, String street, String suburb, String state, String postCode) {
        this.bname = bname;
        this.street = street;
        this.suburb = suburb;
        this.state = state;
        this.postCode = postCode;
        generateRandomCoordinates();
    }

    private void generateRandomCoordinates() {
        double minLat = -38.43; // Minimum latitude
        double maxLat = -37.48; // Maximum latitude
        double minLon = 144.58; // Minimum longitude
        double maxLon = 145.21; // Maximum longitude

        Random random = new Random();
        latitude = minLat + (maxLat - minLat) * random.nextDouble();
        longitude = minLon + (maxLon - minLon) * random.nextDouble();
    }

    public Double getLatitude() {
        if (latitude == null) {
            generateRandomCoordinates();
        }
        return latitude;
    }

    public Double getLongitude() {
        if (longitude == null) {
            generateRandomCoordinates();
        }
        return longitude;
    }
    
    
}