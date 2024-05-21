package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.CartLineItemDTO;
import com.service.foodorderserviceserver.Entity.CartLineItem;
import com.service.foodorderserviceserver.Mapper.CartLineItemMapper;
import com.service.foodorderserviceserver.Mapper.CartMapper;
import com.service.foodorderserviceserver.Service.CartService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;
    private final CartLineItemMapper cartLineItemMapper;
    private final CartMapper cartMapper;


    public CartController(CartService cartService, CartLineItemMapper cartLineItemMapper, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartLineItemMapper = cartLineItemMapper;
        this.cartMapper = cartMapper;
    }

    // List off methods have to do:
    // 1. Show all the cart line items in the cart
    // 2. Show the cart line item by id
    // 3. Add a new cart line item to the cart
    // 4. Update the cart line item
    // 5. Delete the cart line item

    @GetMapping("/{cartId}/items")
    public Result getAllCartLineItemsInCart(@PathVariable Integer cartId) {
        List<CartLineItem> cartLineItems = cartService.getAllCartLineItemsInCart(cartId);
        List<CartLineItemDTO> cartLineItemDTOS = cartLineItems.stream()
                .map(cartLineItemMapper::convertToDto)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Cart line items found", cartLineItemDTOS);
    }

    public Result getCartLineItemIdByCartId(@PathVariable Integer cartId, @PathVariable Integer cartLineItemId) {
        CartLineItem cartLineItem = cartService.getCartLineItemIdByCartId(cartId, cartLineItemId);
        CartLineItemDTO cartLineItemDTO = cartLineItemMapper.convertToDto(cartLineItem);
        return new Result(true, StatusCode.SUCCESS, "Cart line item found", cartLineItemDTO);
    }

@PostMapping("/add")
public Result addItemToCart(@RequestParam("cartId") Integer cartId, @RequestParam("itemId") Integer itemId) {
    CartLineItem cartLineItem = cartService.addItemToCart(cartId, itemId);
    CartLineItemDTO cartLineItemDTO = cartLineItemMapper.convertToDto(cartLineItem);
    return new Result(true, StatusCode.SUCCESS, "Item added to cart", cartLineItemDTO);
}

    // Only Quantity can be updated
    @PutMapping("/{cartId}/items/{cartLineItemId}")
    public Result updateCartLineItem(@PathVariable Integer cartLineItemId, @RequestBody CartLineItemDTO cartLineItemDTO) {
        CartLineItem cartLineItem = cartLineItemMapper.convertToEntity(cartLineItemDTO);
        CartLineItem cartLineItemTrans = cartService.updateCartLineItem(cartLineItemId, cartLineItem);
        CartLineItemDTO cartLineItemDTO1 = cartLineItemMapper.convertToDto(cartLineItemTrans);
        return new Result(true, StatusCode.SUCCESS, "Cart line item updated", cartLineItemDTO1);
    }

    @DeleteMapping("/{cartId}/items/{cartLineItemId}")
    public Result deleteCartLineItem(@PathVariable Integer cartLineItemId) {
        cartService.deleteCartLineItem(cartLineItemId);
        return new Result(true, StatusCode.SUCCESS, "Cart line item deleted");
    }


}
