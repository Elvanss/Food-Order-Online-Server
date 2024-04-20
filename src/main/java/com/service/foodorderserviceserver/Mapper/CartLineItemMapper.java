package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.CartItemRequestDTO;
import com.service.foodorderserviceserver.DTO.CartItemResponseDTO;
import com.service.foodorderserviceserver.Entity.CartLineItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartLineItemMapper {

    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;

    public CartLineItemMapper(ItemMapper itemMapper, CartMapper cartMapper) {
        this.itemMapper = itemMapper;
        this.cartMapper = cartMapper;
    }

    public CartItemResponseDTO convertToDto(CartLineItem cartLineItem) {
        CartItemResponseDTO cartItemResDTO = new CartItemResponseDTO();
        cartItemResDTO.setId(cartLineItem.getId());
        cartItemResDTO.setCartId(cartLineItem.getCartId() != null
                ? this.cartMapper.convertToDto(cartLineItem.getCartId())
                : null);
        cartItemResDTO.setVariantProductId(cartLineItem.getProductId() != null
                ? itemMapper.convertToDto(cartLineItem.getProductId())
                : null);
        cartItemResDTO.setQuantity(cartLineItem.getQuantity());
        cartItemResDTO.setTotalPrice(cartLineItem.getTotalPrice());
        cartItemResDTO.setAddedDate(cartLineItem.getAddedDate());
        cartItemResDTO.setDeleted(cartLineItem.isDeleted());
        return cartItemResDTO;
    }

    public List<CartItemResponseDTO> listCartItemsToListCartItems(List<CartLineItem> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CartLineItem convertToEntity(CartItemRequestDTO cartItemResDTO) {
        CartLineItem cartLineItem = new CartLineItem();
//        cartLineItem.setId(cartItemResDTO.getId());
        cartLineItem.setCartId(cartItemResDTO.getCartId() != null
                ? this.cartMapper.convertToEntity(cartItemResDTO.getCartId())
                : null);
        cartLineItem.setProductId(cartItemResDTO.getProductId() != null
                ? itemMapper.convertToEntity(cartItemResDTO.getProductId())
                : null);
        cartLineItem.setQuantity(cartItemResDTO.getQuantity());
        cartLineItem.setTotalPrice(cartItemResDTO.getProductId().getPrice());
        cartLineItem.setAddedDate(cartItemResDTO.getCartId().getCreatedDate());
        cartLineItem.setDeleted(cartItemResDTO.getProductId().isAvailable());
        return cartLineItem;
    }

}
