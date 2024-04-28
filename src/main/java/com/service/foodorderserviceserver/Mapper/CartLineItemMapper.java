package com.service.foodorderserviceserver.Mapper;

import com.service.foodorderserviceserver.DTO.CartDTO;
import com.service.foodorderserviceserver.DTO.CartLineItemDTO;
import com.service.foodorderserviceserver.DTO.ItemDTO;
import com.service.foodorderserviceserver.Entity.Cart;
import com.service.foodorderserviceserver.Entity.CartLineItem;
import com.service.foodorderserviceserver.Entity.Item;
import com.service.foodorderserviceserver.Repository.CartRepository;
import com.service.foodorderserviceserver.Repository.ItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartLineItemMapper {

    public CartLineItemDTO convertToDto(CartLineItem cartLineItem) {
    CartLineItemDTO cartLineItemDTO = new CartLineItemDTO();
    cartLineItemDTO.setId(cartLineItem.getId());

    CartDTO cartDTO = new CartDTO();
    cartDTO.setId(cartLineItem.getCartId().getId());
    cartLineItemDTO.setCartId(cartDTO);

    ItemDTO itemDTO = new ItemDTO();
    itemDTO.setId(cartLineItem.getProductId().getId());
    cartLineItemDTO.setVariantProductId(itemDTO);

    cartLineItemDTO.setQuantity(cartLineItem.getQuantity());
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
        Cart cart = new Cart();
//        cart.setId(cartItemRequestDTO.getCartId());
//        cartLineItem.setCartId(cart);
        cart.setId(cartItemRequestDTO.getCartId().getId());
        cartLineItem.setCartId(cart);
        Item variantProduct = new Item();
        cartLineItem.setProductId(variantProduct);
        cartLineItem.setQuantity(cartItemRequestDTO.getQuantity());
        cartLineItem.setTotalPrice(cartItemRequestDTO.getTotalPrice());
        return cartLineItem;
    }
}
