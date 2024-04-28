package com.service.foodorderserviceserver.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.service.foodorderserviceserver.Entity.Type.ItemCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "item-name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "isAvailable")
    private boolean isAvailable;

    @Column(name = "price")
    private Double price;

    @Column(name = "itemCategory")
    private ItemCategory itemCategory;

    @ManyToOne
    @JoinColumn(name = "restaurant-id", referencedColumnName = "id")
    private Restaurant restaurantId;

}
