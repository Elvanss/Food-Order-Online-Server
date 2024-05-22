package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.OrderDTO;
import com.service.foodorderserviceserver.Entity.Order;
import com.service.foodorderserviceserver.Mapper.OrderMapper;
import com.service.foodorderserviceserver.Service.OrderService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/viewOrders")
    public Result viewOrdersByRestaurant(@RequestParam("restaurantId") Integer restaurantId) {
        List<Order> orders = orderService.viewOrderFromUserInRestaurantSide(restaurantId);
        List<OrderDTO> orderDTOS = orders.stream()
                .map(orderMapper::convertToDto)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "All Orders", orderDTOS);
    }

    @PostMapping("/createOrder")
    public Result createOrder(@RequestParam("userId") Integer userId,
                              @RequestParam("restaurantId") Integer restaurantId) {
        Order order = orderService.createOrder(userId, restaurantId);
        OrderDTO orderDTO = orderMapper.convertToDto(order);
        return new Result(true, StatusCode.SUCCESS, "Order Created", orderDTO);
    }


}