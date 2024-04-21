package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Cart;
import com.service.foodorderserviceserver.Entity.CartLineItem;
import com.service.foodorderserviceserver.Entity.Item;
import com.service.foodorderserviceserver.Mapper.CartLineItemMapper;
import com.service.foodorderserviceserver.Repository.CartLineItemRepository;
import com.service.foodorderserviceserver.Repository.CartRepository;
import com.service.foodorderserviceserver.Repository.ItemRepository;
import com.service.foodorderserviceserver.System.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartLineItemService {
    private static final Logger log = LoggerFactory.getLogger(CartLineItemService.class);
    private final CartRepository cartRepository;
    private final ItemRepository variantProductRepository;
    private final CartLineItemRepository cartLineItemRepository;
    private final CartLineItemMapper cartLineItemMapper;

    public CartLineItemService(CartRepository cartRepository, ItemRepository variantProductRepository, CartLineItemRepository cartLineItemRepository, CartLineItemMapper cartLineItemMapper) {
        this.cartRepository = cartRepository;
        this.variantProductRepository = variantProductRepository;
        this.cartLineItemRepository = cartLineItemRepository;
        this.cartLineItemMapper = cartLineItemMapper;
    }

    public CartLineItem createCartItem(CartLineItem item) {
        // get variant product by id
        Item variantProduct = getVariantProductById(item.getProductId().getId());
        log.info("Variant product: " + variantProduct.getId());

        // check if the cart line item already exists in the cart
        Optional<CartLineItem> foundCartLineItem = cartLineItemRepository.findByVariantProductIdAndNotDeteted (
                                                                                            item.getCartId().getId(),
                                                                                            item.getId());
        CartLineItem cartLineItem;
        if (foundCartLineItem.isPresent()) {
            log.warn("Found cart line item in cart");
            cartLineItem = foundCartLineItem.get();
            cartLineItem.setQuantity(cartLineItem.getQuantity() + item.getQuantity());
            cartLineItem.setTotalPrice(variantProduct.getPrice() * cartLineItem.getQuantity());
            cartLineItem.setAddedDate(new Date());
            cartLineItemRepository.save(cartLineItem);
        } else {
            cartLineItem = new CartLineItem();
            log.warn("Create new cart line item");
            cartLineItem.setCartId(item.getCartId());
            cartLineItem.setProductId(item.getProductId());
            cartLineItem.setQuantity(item.getQuantity());
            cartLineItem.setTotalPrice(variantProduct.getPrice() * item.getQuantity());
            cartLineItem.setAddedDate(new Date());
            cartLineItem.setDeleted(false);
            cartLineItemRepository.save(cartLineItem);
        }

        //update the total price and update data in cart after add item in cart_line_items'
        updateCartTotalPrice(item.getCartId().getId());
        return cartLineItemRepository.save(cartLineItem);
    }

    private void updateCartTotalPrice(Integer id) {
        Optional<Cart> foundCart = cartRepository.findById(id);
        if (foundCart.isEmpty()) {
            log.error("Cart with id {} is not found", id);
            throw new AppException(HttpStatus.NOT_FOUND.value(), "Cart not found");
        }

        Cart cart = foundCart.get();
        List<CartLineItem> cartLineItems = cartLineItemRepository.findAllByCartId(id);
        double totalPrice = cartLineItems.stream().mapToDouble(CartLineItem::getTotalPrice).sum();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }

    public CartLineItem deleteAfterOrder(Integer id, Integer orderId) {
        Optional<CartLineItem> found = cartLineItemRepository.findById(id);
        if (found.isEmpty()){
            log.error("CartLineItem with id " + id + " is not found");
            throw new AppException(HttpStatus.NOT_FOUND.value(), "CartLineItem not found");
        }

        CartLineItem cartLineItem = found.get();
        cartLineItem.setDeleted(true);
//        cartLineItem.setOrderId(orderId);
        return cartLineItemRepository.save(cartLineItem);
    }


    private Item getVariantProductById(Integer id){
        Optional<Item> foundVariantProduct = variantProductRepository.findById(id);
        if (foundVariantProduct.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), "Variant product not found");
        }
        return foundVariantProduct.get();
    }

    public List<CartLineItem> getListCartLineItemsByCartId(Integer id) {
        List<CartLineItem> listCartItems = cartLineItemRepository.findAllByCartId(id);
        if (listCartItems.isEmpty()){
            throw new AppException(HttpStatus.NO_CONTENT.value(), "Cart is empty");
        }
        return listCartItems;
    }

}
