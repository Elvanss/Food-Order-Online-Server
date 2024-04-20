package com.service.foodorderserviceserver.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.service.foodorderserviceserver.Entity.Address.Address;
import com.service.foodorderserviceserver.Entity.Address.RestaurantAddress;
import com.service.foodorderserviceserver.Entity.Type.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Time;
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

    @Column(name = "email") // Represent the restaurant email
    private String email;

    @Column(name = "password") // Represent the restaurant password
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "cuisine") // Represent the cuisine type {VNese, Chinese, Aussie}
    private String cuisine;

    @Column(name = "open-time")
    private Time openTime;

    @Column(name = "close-time")
    private Time closeTime;

    @Column(name = "isOpened")
    private boolean isOpened;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="roles")
    private Roles roles;

//    @JsonIgnore
    @OneToOne(cascade = CascadeType.MERGE ,fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private RestaurantAddress address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurantId", cascade = CascadeType.ALL)
    private List<Item> itemList;

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
