package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.CartDTO;
import com.service.foodorderserviceserver.Entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    private final UserMapper userMapper;

    public CartMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CartDTO convertToDto(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setUser(cart.getUser() != null
                        ? this.userMapper.convertToDto(cart.getUser())
                        : null);
        cartDTO.setNumberOfCartLineItems(cart.numberOfCartLineItems());
        return cartDTO;
    }

    public Cart convertToEntity(CartDTO cartId) {
        Cart cart = new Cart();
        cart.setId(cartId.getId());
        cart.setTotalPrice(cartId.getTotalPrice());
        return cart;
    }
}
