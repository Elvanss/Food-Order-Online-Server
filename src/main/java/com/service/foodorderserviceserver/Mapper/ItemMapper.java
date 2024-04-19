package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.ItemDTO;
import com.service.foodorderserviceserver.Entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    private final RestaurantMapper restaurantMapper;

    public ItemMapper(RestaurantMapper restaurantMapper) {
        this.restaurantMapper = restaurantMapper;
    }

    public ItemDTO convertToDto(Item source) {
        return new ItemDTO(source.getId(),
                           source.getItemName(),
                           source.getDescription(),
                           source.isAvailable(),
                           source.getPrice(),
                           source.getItemCategory(),
                           source.getRestaurantId() != null
                                   ? this.restaurantMapper.convertToDto(source.getRestaurantId())
                                   : null);
    }

    public Item convertToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setItemName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setAvailable(itemDTO.isAvailable());
        item.setPrice(itemDTO.getPrice());
        item.setItemCategory(itemDTO.getItemCategory());
        item.setRestaurantId(itemDTO.getRestaurantId() != null
                             ? this.restaurantMapper.convertToEntity(itemDTO.getRestaurantId())
                             : null);
        return item;
    }
}
