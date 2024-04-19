package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Item;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Repository.ItemRepository;
import com.service.foodorderserviceserver.Repository.RestaurantRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final RestaurantRepository restaurantRepository;

    public ItemService(ItemRepository itemRepository,
                       RestaurantRepository restaurantRepository) {

        this.itemRepository = itemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // Show all the items in specific restaurant
    public List<Item> getAllItemsInRestaurant(Integer restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        return this.itemRepository.findByRestaurant(restaurant);
    }

    // Show all the items in the system
    public List<Item> getAllItems() {
        return this.itemRepository.findAll();
    }

    // Show the item by id
    public Item getItemById(Integer itemId) {
        return itemRepository
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
    }

    // Show the item by id of restaurant
    public Item getItemByIdOfRestaurant(Integer restaurantId, Integer itemId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        return itemRepository
                .findByIdAndRestaurantId(itemId, restaurant)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
    }

    // Add a new item to that restaurant
    public Item addItemToRestaurant(Integer restaurantId, Item Item) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        Item item = new Item();
        item.setItemName(Item.getItemName());
        item.setDescription(Item.getDescription());
        item.setAvailable(Item.isAvailable());
        item.setPrice(Item.getPrice());
        item.setItemCategory(Item.getItemCategory());
        item.setRestaurantId(restaurant);
        itemRepository.save(item);
        return item;
    }

    public Item addItemsToRestaurant(Integer restaurantId, List<Item> items) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        for (Item item : items) {
            item.setItemName(item.getItemName());
            item.setDescription(item.getDescription());
            item.setAvailable(item.isAvailable());
            item.setPrice(item.getPrice());
            item.setItemCategory(item.getItemCategory());
            item.setRestaurantId(restaurant);
            itemRepository.save(item);
        }
        return items.get(0);
    }

    // Update the item by id of restaurant
    public Item updateItemOfRestaurant(Integer restaurantId, Integer itemId, Item item) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        Item itemValid = itemRepository
                .findByIdAndRestaurantId(itemId, restaurant)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        itemValid.setItemName(item.getItemName());
        itemValid.setDescription(item.getDescription());
        itemValid.setAvailable(item.isAvailable());
        itemValid.setItemCategory(item.getItemCategory());
        itemRepository.save(itemValid);
        return item;
    }

    // Delete the item by id of restaurant
    public void deleteItemOfRestaurant(Integer restaurantId, Integer itemId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        Item item = itemRepository
                .findByIdAndRestaurantId(itemId, restaurant)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        itemRepository.delete(item);
    }

    // Delete the item by id
    public void deleteItem(Integer itemId) {
        Item item = itemRepository
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        itemRepository.delete(item);
    }

    // Delete all the items in the system
    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    // Delete all the items in the restaurant
    public void deleteAllItemsInRestaurant(Integer restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ObjectNotFoundException(restaurantId, "Restaurant not found"));
        List<Item> items = itemRepository.findByRestaurant(restaurant);
        itemRepository.deleteAll(items);
    }

    // Update the price when the items sale
    public void updatePrice(Integer itemId, double price) {
        Item item = itemRepository
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        item.setPrice(price);
        itemRepository.save(item);
    }


//    public void findItemSales(Integer itemId) {
//        Item item = itemRepository
//                .findById(itemId)
//                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
//        item.getSales();
//    }
}
