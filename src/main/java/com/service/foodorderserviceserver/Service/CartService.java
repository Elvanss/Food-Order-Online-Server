package com.service.foodorderserviceserver.Service;

import com.service.foodorderserviceserver.Entity.Cart;
import com.service.foodorderserviceserver.Entity.CartLineItem;
import com.service.foodorderserviceserver.Entity.Item;
import com.service.foodorderserviceserver.Repository.CartLineItemRepository;
import com.service.foodorderserviceserver.Repository.CartRepository;
import com.service.foodorderserviceserver.Repository.ItemRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {


    private final CartRepository cartRepository;
    private final CartLineItemRepository cartLineItemRepository;
    private final ItemRepository itemRepository;

    public CartService(CartRepository cartRepository, CartLineItemRepository cartLineItemRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.cartLineItemRepository = cartLineItemRepository;
        this.itemRepository = itemRepository;
    }
    // List off methods have to do:
    // 1. Show all the cart line items in the cart
    // 2. Show the cart line item by id
    // 3. Add a new cart line item to the cart
    // 4. Update the cart line item
    // 5. Delete the cart line item

    public List<CartLineItem> getAllCartLineItemsInCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ObjectNotFoundException(cartId, "Cart not found"));
        return this.cartLineItemRepository.findAllByCartId(cart.getId());
    }

    public CartLineItem getCartLineItemIdByCartId(Integer cartId, Integer cartLineItemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ObjectNotFoundException(cartId, "Cart not found"));

        CartLineItem cartLineItem = cartLineItemRepository.findById(cartLineItemId)
                .orElseThrow(() -> new ObjectNotFoundException(cartLineItemId, "Cart line item not found"));

        if (!cartLineItem.getCartId().equals(cart)) {
            throw new IllegalArgumentException("Cart line item does not belong to the specified cart");
        }

        return cartLineItem;
    }

    public CartLineItem addItemToCart(Integer cartId, Integer itemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ObjectNotFoundException(cartId, "Cart not found"));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException(itemId, "Item not found"));
        CartLineItem newCartLineItem = new CartLineItem();
        newCartLineItem.setCartId(cart);
        newCartLineItem.setProductId(item);
        newCartLineItem.setQuantity(1);
        newCartLineItem.setTotalPrice(item.getPrice());
        if (newCartLineItem != null) {
            List<CartLineItem> cartLineItems = cartLineItemRepository.findAllByCartId(cart.getId());
            for (CartLineItem cartLineItem : cartLineItems) {
                if (cartLineItem.getProductId().getId().equals(itemId)) {
                    cartLineItem.setQuantity(cartLineItem.getQuantity() + 1);
                    cartLineItem.setTotalPrice(cartLineItem.getQuantity() * item.getPrice());
                    return this.cartLineItemRepository.save(cartLineItem);
                }
            }
        }
        return this.cartLineItemRepository.save(newCartLineItem);
    }

    public CartLineItem updateCartLineItem(Integer cartLineItemId, CartLineItem newCartLineItem) {
        CartLineItem existingCartLineItem = cartLineItemRepository.findById(cartLineItemId)
                .orElseThrow(() -> new ObjectNotFoundException(cartLineItemId, "Cart line item not found"));
        existingCartLineItem.setQuantity(newCartLineItem.getQuantity());
        return this.cartLineItemRepository.save(existingCartLineItem);
    }

    public void deleteCartLineItem(Integer cartLineItemId) {
        CartLineItem cartLineItem = cartLineItemRepository.findById(cartLineItemId)
                .orElseThrow(() -> new ObjectNotFoundException(cartLineItemId, "Cart line item not found"));
        cartLineItemRepository.save(cartLineItem);
    }
}
