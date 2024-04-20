package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Cart;
import com.service.foodorderserviceserver.Entity.CartLineItem;
import com.service.foodorderserviceserver.Mapper.CartLineItemMapper;
import com.service.foodorderserviceserver.Mapper.CartMapper;
import com.service.foodorderserviceserver.Repository.CartLineItemRepository;
import com.service.foodorderserviceserver.Repository.CartRepository;
import com.service.foodorderserviceserver.System.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartLineItemRepository cartLineItemRepository;
    private final CartLineItemMapper cartLineItemMapper;
    private final CartMapper cartMapper;

    public CartService(CartRepository cartRepository,
                       CartLineItemRepository cartLineItemRepository,
                       CartLineItemMapper cartLineItemMapper,
                       CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartLineItemRepository = cartLineItemRepository;
        this.cartLineItemMapper = cartLineItemMapper;
        this.cartMapper = cartMapper;
    }

    public Cart findById(Integer id) {
        return getCartById(id);
    }

    private Cart getCartById(Integer id){
        Optional<Cart> foundCart = cartRepository.findById(id);
        if (foundCart.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), "Cart not found");
        }
        return foundCart.get();
    }

    private List<CartLineItem> getListCartLineItemsByCartId(Integer cartId){
        List<CartLineItem> listCartItems = cartLineItemRepository.findAllByCartId(cartId);
        if (listCartItems.isEmpty()){
            throw new AppException(HttpStatus.NO_CONTENT.value(), "Cart is empty");
        }
        return listCartItems;
    }


}
