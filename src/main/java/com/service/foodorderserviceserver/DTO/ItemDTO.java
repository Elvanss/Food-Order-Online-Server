package com.service.foodorderserviceserver.DTO;

import com.service.foodorderserviceserver.Entity.Type.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {
    private Integer id;
    private String name;
    private String description;
    private boolean isAvailable;
    private Double price;
    private ItemCategory itemCategory;
    private String imageUrl;
    private RestaurantDTO restaurantId;
}
