package com.service.foodorderserviceserver.Service;

import com.paypal.base.rest.PayPalRESTException;
import com.service.foodorderserviceserver.Entity.*;
import com.service.foodorderserviceserver.Entity.Type.OrderStatus;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.OrderRepository;
import com.service.foodorderserviceserver.Service.Payment.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final AddressService addressService;
    private final CartService cartService;
    private final PaypalService paypalService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        RestaurantService restaurantService,
                        AddressService addressService,
                        CartService cartService,
                        PaypalService paypalService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.addressService = addressService;
        this.cartService = cartService;
        this.paypalService = paypalService;
    }

    @Transactional
    public List<Order> viewOrderFromUserInRestaurantSide(Integer restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return orderRepository.findAllOrderByUserAndRestaurant(restaurant);
    }

    @Transactional
    public Order createOrder(Integer userId, Integer restaurantId) {
        User user = userService.findById(userId);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        Address address = user.getAddresses().get(0);
        Cart cart = cartService.getCartForUser(user);
        Order order = initializeOrder(user, restaurant, address, cart);

        double totalPrice = calculateTotalPriceAndPopulateOrderLineItems(cart, order);

        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);

        processOrderPayment(savedOrder);

        return savedOrder;
    }

    private Order initializeOrder(User user, Restaurant restaurant, Address address, Cart cart) {
        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setAddress(address);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        return order;
    }

    private double calculateTotalPriceAndPopulateOrderLineItems(Cart cart, Order order) {
        double totalPrice = 0;
        List<OrderLineItem> orderLineItems = new ArrayList<>();
        for (CartLineItem cartLineItem : cart.getCartLineItems()) {
            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setItemId(cartLineItem.getProductId());
            orderLineItem.setQuantity(cartLineItem.getQuantity());
            orderLineItem.setPrice(cartLineItem.getTotalPrice());
            orderLineItem.setOrder(order);
            orderLineItems.add(orderLineItem);
            totalPrice += cartLineItem.getTotalPrice();
        }
        order.setOrderLineItems(orderLineItems);
        return totalPrice;
    }

    private void processOrderPayment(Order savedOrder) {
        try {
            Map<String, Object> paymentResponse = paypalService.createOrderPayment(savedOrder.getId().toString(),
                    savedOrder.getTotalPrice());
            if (paymentResponse.containsKey("redirect_url")) {
                savedOrder.setStatus(OrderStatus.PROCESSING);
                orderRepository.save(savedOrder);
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException("Payment failed: " + e.getMessage(), e);
        } finally {
            cartService.clearCartForUser(savedOrder.getUser());
        }
    }

    public Order findOrder (Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
