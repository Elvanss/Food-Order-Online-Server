package com.service.foodorderserviceserver.Entity;

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
@Table(name = "restaurant")
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

    @OneToOne // Only one address can be assigned to the restaurant
    @JoinColumn(name = "address-id", referencedColumnName = "id")
    private RestaurantAddress address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurantId", cascade = CascadeType.ALL)
    // Show the list of items belong to restaurant
    private List<Item> itemList;


    public int getNumberOfItems() {
        return itemList.size();
    }

    public void addItem(Item item) {
        this.itemList.add(item);
        item.setRestaurantId(this);
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
