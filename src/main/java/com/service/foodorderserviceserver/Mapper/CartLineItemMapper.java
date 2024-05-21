package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.CartLineItemDTO;
import com.service.foodorderserviceserver.Entity.CartLineItem;
import org.springframework.stereotype.Component;

@Component
public class CartLineItemMapper {

    private final CartMapper cartMapper;
    private final ItemMapper itemMapper;

    public CartLineItemMapper(CartMapper cartMapper, ItemMapper itemMapper) {
        this.cartMapper = cartMapper;
        this.itemMapper = itemMapper;
    }

    public CartLineItemDTO convertToDto(CartLineItem cartLineItem) {
    CartLineItemDTO cartLineItemDTO = new CartLineItemDTO();
    cartLineItemDTO.setId(cartLineItem.getId());
    cartLineItemDTO.setQuantity(cartLineItem.getQuantity());
    cartLineItemDTO.setTotalPrice(cartLineItem.getTotalPrice());
    cartLineItemDTO.setCartId(cartLineItem.getCartId() != null
            ? this.cartMapper.convertToDto(cartLineItem.getCartId())
            : null);
    cartLineItemDTO.setVariantProductId(cartLineItem.getProductId() != null
            ? this.itemMapper.convertToDto(cartLineItem.getProductId())
            : null);
    return cartLineItemDTO;
}

//    public CartLineItem convertToEntity(CartLineItemDTO cartItemRequestDTO) {
//        CartLineItem cartLineItem = new CartLineItem();
//        cartLineItem.setId(cartItemRequestDTO.getId());
//        Cart cart = new Cart();
//        cart.setId(cartItemRequestDTO.getCartId());
//        cartLineItem.setCartId(cart);
//        Item variantProduct = new Item();
//        variantProduct.setId(cartItemRequestDTO.getVariantProductId());
//        cartLineItem.setProductId(variantProduct);
//        cartLineItem.setQuantity(cartItemRequestDTO.getQuantity());
//        return cartLineItem;
//    }


    public CartLineItem convertToEntity(CartLineItemDTO cartItemRequestDTO) {
        CartLineItem cartLineItem = new CartLineItem();
        cartLineItem.setId(cartItemRequestDTO.getId());
        cartLineItem.setQuantity(cartItemRequestDTO.getQuantity());
        cartLineItem.setTotalPrice(cartItemRequestDTO.getTotalPrice());
        return cartLineItem;
    }
}
