package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.ItemDTO;
import com.service.foodorderserviceserver.Entity.Item;
import com.service.foodorderserviceserver.Mapper.ItemMapper;
import com.service.foodorderserviceserver.Service.ItemService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    // Restaurant with Items Side
    @GetMapping("/restaurant/{restaurantId}")
    public Result getAllItemsInRestaurant(@PathVariable int restaurantId) {
        List<Item> item = itemService.getAllItemsInRestaurant(restaurantId);
        List<ItemDTO> itemDTOs = item.stream()
                .map(this.itemMapper::convertToDto)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Items retrieved successfully", itemDTOs);
    }

    @GetMapping("/restaurant/{restaurantId}/{itemId}")
    public Result getItemByIdOfRestaurant (@PathVariable int restaurantId, @PathVariable int itemId) {
        Item item = itemService.getItemByIdOfRestaurant(restaurantId, itemId);
        ItemDTO itemDTO = itemMapper.convertToDto(item);
        return new Result(true, StatusCode.SUCCESS, "Item retrieved successfully", itemDTO);
    }

    @PostMapping("/restaurant/single/{restaurantId}")
    public Result addItemToRestaurant(@PathVariable int restaurantId, @RequestBody ItemDTO itemDTO) {
        Item item = itemMapper.convertToEntity(itemDTO);
        Item addedItem = itemService.addItemToRestaurant(restaurantId, item);
        ItemDTO addedItemDTO = itemMapper.convertToDto(addedItem);
        return new Result(true, StatusCode.SUCCESS, "Item added successfully", addedItemDTO);
    }

    @PostMapping("/restaurant/multi/{restaurantId}")
    public Result addItemsToRestaurant(@PathVariable int restaurantId, @RequestBody List<ItemDTO> itemDTOs) {
        List<Item> items = itemDTOs.stream()
                .map(itemMapper::convertToEntity)
                .collect(Collectors.toList());
        Item firstAddedItem = itemService.addItemsToRestaurant(restaurantId, items);
        ItemDTO addedItemDTO = itemMapper.convertToDto(firstAddedItem);
        return new Result(true, StatusCode.SUCCESS, "Items added successfully", addedItemDTO);
    }

    @PutMapping("/restaurant/{restaurantId}/{itemId}")
    public Result updateItemOfRestaurant(@PathVariable int restaurantId, @PathVariable int itemId, @RequestBody ItemDTO itemDTO) {
        Item item = itemMapper.convertToEntity(itemDTO);
        Item updatedItem = itemService.updateItemOfRestaurant(restaurantId, itemId, item);
        ItemDTO updatedItemDTO = itemMapper.convertToDto(updatedItem);
        return new Result(true, StatusCode.SUCCESS, "Item updated successfully", updatedItemDTO);
    }

    @DeleteMapping("/restaurant/{restaurantId}/{itemId}")
    public Result deleteItemOfRestaurant(@PathVariable int restaurantId, @PathVariable int itemId) {
        itemService.deleteItemOfRestaurant(restaurantId, itemId);
        return new Result(true, StatusCode.SUCCESS, "Item deleted successfully");
    }

    @DeleteMapping("/deleteAll/{restaurantId}")
    public Result deleteAllItemsInRestaurant(@PathVariable Integer restaurantId) {
        itemService.deleteAllItemsInRestaurant(restaurantId);
        return new Result(true, StatusCode.SUCCESS, "All items deleted successfully");
    }

    // Isolated Item Side
    @GetMapping
    public Result getAllItems() {
        List<Item> item = itemService.getAllItems();
        List<ItemDTO> itemDTOs = item.stream()
                .map(this.itemMapper::convertToDto)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Items retrieved successfully", itemDTOs);
    }

    @GetMapping("/{itemId}")
    public Result findItemById(@PathVariable int itemId) {
        Item item = itemService.getItemById(itemId);
        ItemDTO itemDTO = itemMapper.convertToDto(item);
        return new Result(true, StatusCode.SUCCESS, "Item retrieved successfully", itemDTO);
    }

    @DeleteMapping("/{itemId}")
    public Result deleteItem(@PathVariable int itemId) {
        itemService.deleteItem(itemId);
        return new Result(true, StatusCode.SUCCESS, "Item deleted successfully");
    }

    // Item Sales
    @GetMapping("/sales/{itemId}")
    public Result getItemSales(@PathVariable int itemId) {
        Item item = itemService.getItemById(itemId);
        ItemDTO itemDTO = itemMapper.convertToDto(item);
        return new Result(true, StatusCode.SUCCESS, "Item sales retrieved successfully", itemDTO);
    }

//    @PutMapping("/sales/{itemId}")
//    public Result updateItemSales(@PathVariable int itemId, @RequestBody ItemDTO itemDTO) {
//        Item item = itemMapper.convertToEntity(itemDTO);
//        Item updatedItem = itemService.updatePrice(itemId, item);
//        ItemDTO updatedItemDTO = itemMapper.convertToDto(updatedItem);
//        return new Result(true, StatusCode.SUCCESS, "Item sales updated successfully", updatedItemDTO);
//    }



}
