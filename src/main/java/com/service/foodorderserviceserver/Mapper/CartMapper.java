package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.CartDTO;
import com.service.foodorderserviceserver.DTO.CartLineItemDTO;
import com.service.foodorderserviceserver.DTO.CartResponseDTO;
import com.service.foodorderserviceserver.DTO.User.UserDTO;
import com.service.foodorderserviceserver.Entity.Cart;
import com.service.foodorderserviceserver.Mapper.User.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

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
        UserDTO userDTO = new UserDTO();
        userDTO.setId(cart.getUser().getId());
        cartDTO.setUserId(userDTO);
        return cartDTO;
    }

    //This method is use for convert Cart to CartResponseDTO to response to client
    public CartResponseDTO convertToResponseDto(Cart cart, List<CartLineItemDTO> cartItemResponseDTOList) {
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        cartResponseDTO.setId(cart.getId());
        cartResponseDTO.setTotalPrice(cart.getTotalPrice());
        cartResponseDTO.setUserId(cart.getUser() != null
                                    ? this.userMapper.convertToDto(cart.getUser())
                                    : null);
        cartResponseDTO.setCartItems(cartItemResponseDTOList);
        return cartResponseDTO;
    }

    public Cart convertToEntity(CartDTO cartId) {
        Cart cart = new Cart();
        cart.setId(cartId.getId());
        return cart;
    }
}
