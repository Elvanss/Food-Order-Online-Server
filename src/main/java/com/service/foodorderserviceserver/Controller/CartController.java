package com.service.foodorderserviceserver.Controller;

import com.service.foodorderserviceserver.DTO.CartDTO;
import com.service.foodorderserviceserver.DTO.CartLineItemDTO;
import com.service.foodorderserviceserver.DTO.CartResponseDTO;
import com.service.foodorderserviceserver.Entity.Cart;
import com.service.foodorderserviceserver.Entity.CartLineItem;
import com.service.foodorderserviceserver.Mapper.CartLineItemMapper;
import com.service.foodorderserviceserver.Mapper.CartMapper;
import com.service.foodorderserviceserver.Service.CartLineItemService;
import com.service.foodorderserviceserver.Service.CartService;
import com.service.foodorderserviceserver.System.Result;
import com.service.foodorderserviceserver.System.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {
    private final CartLineItemService cartLineItemService;
    private final CartService cartService;
    private final CartLineItemMapper cartLineItemMapper;
    private final CartMapper cartMapper;

    @Autowired
    public CartController(CartLineItemService cartLineItemService, CartService cartService, CartLineItemMapper cartLineItemMapper, CartMapper cartMapper) {
        this.cartLineItemService = cartLineItemService;
        this.cartService = cartService;
        this.cartLineItemMapper = cartLineItemMapper;
        this.cartMapper = cartMapper;
    }

//    @PostMapping("/add-to-cart")
//    public ResponseEntity<ResponseDTO<CartItemResDTO>> addToCart(@RequestBody CartItemReqDTO cartItem){
//        log.info("Creating CartItem with variant product id " + cartItem.getVariantProductId());
//        CartItemResDTO res = cartLineItemService.createCartItem(cartItem);
//        return success(res);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseDTO<CartResDTO>> viewCart(@PathVariable(name = "id") Long id){
//        CartResDTO res = cartService.findById(id);
//        return success(res);
//    }

//    @PostMapping("/add-to-cart") // Add to Cart
//    public Result addToCart(@RequestBody CartItemRequestDTO cartItemRequestDTO){
//        log.info("Creating CartItem with variant product id " + cartItemRequestDTO.getProductId());
//        CartLineItem cartLineItem = cartLineItemMapper.convertToEntity(cartItemRequestDTO);
//        CartLineItem res = cartLineItemService.createCartItem(cartLineItem);
//        CartItemResponseDTO cartItemResponseDTO = cartLineItemMapper.convertToDto(res);
//        return new Result(true, StatusCode.SUCCESS, "Added Product to Cart", cartItemResponseDTO);
//    }
    // Make another API function for add item to cart by using CartItemRequestDTO and CartItemResponseDTO
    @PostMapping("/add-to-cart")
    public Result addToCart(@RequestBody CartLineItemDTO cartItemRequestDTO) {
        log.info("Creating CartItem with variant product id " + cartItemRequestDTO.getVariantProductId());
        CartLineItem cartLineItem = cartLineItemMapper.convertToEntity(cartItemRequestDTO);
        CartLineItem res = cartLineItemService.createCartItem(cartLineItem);
        CartLineItemDTO cartItemResponseDTO = cartLineItemMapper.convertToDto(res);
        return new Result(true, StatusCode.SUCCESS, "Added Product to Cart", cartItemResponseDTO);
    }




//    @GetMapping("/{id}") // View Cart with no list
//    public Result viewCart(@PathVariable(name = "id") Integer id){
//        Cart cart = cartService.findById(id);
//        CartDTO cartDTO = cartMapper.convertToDto(cart);
//        return new Result(true, StatusCode.SUCCESS, "View Cart", cartDTO);
//    }

    @GetMapping("/{id}") // View Cart
    public Result viewCart(@PathVariable(name = "id") Integer id){
        // Fetch the Cart entity
        Cart cart = cartService.findById(id);

        // Fetch the list of CartLineItem entities associated with the Cart
        List<CartLineItem> cartLineItems = cartLineItemService.getListCartLineItemsByCartId(id);

        // Convert the CartLineItem entities to CartLineItemDTO
        List<CartLineItemDTO> cartLineItemDTOs = cartLineItems.stream()
                .map(cartLineItemMapper::convertToDto)
                .collect(Collectors.toList());

        // Convert the Cart entity and the list of CartLineItemDTO to CartResponseDTO
        CartResponseDTO cartResponseDTO = cartMapper.convertToResponseDto(cart, cartLineItemDTOs);

        return new Result(true, StatusCode.SUCCESS, "View Cart", cartResponseDTO);
    }

    @DeleteMapping("/delete-after-order/{id}/{orderId}")
    public Result deleteAfterOrder(@PathVariable Integer id, // CartLineItem id
                                    @PathVariable Integer orderId) {
        CartLineItem cartLineItem = cartLineItemService.deleteAfterOrder(id, orderId);
        CartLineItemDTO cartItemResponseDTO = cartLineItemMapper.convertToDto(cartLineItem);
        return new Result(true, StatusCode.SUCCESS, "Deleted Cart Line Item After Order", cartItemResponseDTO);
    }

}
