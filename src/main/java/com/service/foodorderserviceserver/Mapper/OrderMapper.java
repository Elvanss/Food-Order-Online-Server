package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.OrderDTO;
import com.service.foodorderserviceserver.Entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;
    private final AddressMapper addressMapper;


    public OrderMapper(UserMapper userMapper, RestaurantMapper restaurantMapper, AddressMapper addressMapper) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
        this.addressMapper = addressMapper;
    }

    public OrderDTO convertToDto(Order source) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(source.getId());
        orderDTO.setUser(source.getUser() != null
                ? userMapper.convertToDto(source.getUser())
                : null);
        orderDTO.setRestaurant(source.getRestaurant() != null
                ? restaurantMapper.convertToDto(source.getRestaurant())
                : null);
        orderDTO.setAddress(source.getAddress() != null
                ? addressMapper.convertToDto(source.getAddress())
                : null);
        orderDTO.setNumberOfOrderItems(source.getNumberOfOrderLineItems());
        orderDTO.setTotalPrice(source.getTotalPrice());
        orderDTO.setOrderDate(source.getOrderDate());
        orderDTO.setStatus(source.getStatus());
        return orderDTO;
    }

    public Order convertToEntity(OrderDTO source) {
        Order order = new Order();
        order.setId(source.getId());
        order.setTotalPrice(source.getTotalPrice());
        order.setOrderDate(source.getOrderDate());
        order.setStatus(source.getStatus());
        return order;

    }
}
