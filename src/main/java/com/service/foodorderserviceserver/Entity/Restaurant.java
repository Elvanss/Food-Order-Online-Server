package com.service.foodorderserviceserver.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Restaurant")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "restaurant-name")
    private String restaurantName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "open-time")
    private Time openTime;

    @Column(name = "close-time")
    private Time closeTime;

    @Column(name = "isOpened")
    private boolean isOpened;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurantId", cascade = CascadeType.ALL)
    private List<Item> itemList;

    // Address Behaviour
    public Integer getNumberOfAddress() {
        return this.addresses.size();
    }

    public void addAddress(Address address) {
        address.setRestaurant(this);
        this.addresses.add(address);
    }

    public void removeAddress(Address addressToBeAssigned) {
        addressToBeAssigned.setRestaurant(null);
        this.addresses.remove(addressToBeAssigned);
    }

    // Items Behaviour
    public int getNumberOfItems() {
        return itemList.size();
    }

    public void addItem(Item item) {
        item.setRestaurantId(this);
        this.itemList.add(item);
    }

    public void removeAllItems() {
        this.itemList.clear();
    }

    public void removeItem(Item item) {
        this.itemList.remove(item);
    }


    // Open and close the restaurant
    @Transactional
    public void openRestaurant() {
        this.isOpened = true;
        for (Item item : this.itemList) {
            item.setAvailable(true);
        }
    }

    @Transactional
    public void closeRestaurant() {
        this.isOpened = false;
        for (Item item : this.itemList) {
            item.setAvailable(false);
        }
    }

}
