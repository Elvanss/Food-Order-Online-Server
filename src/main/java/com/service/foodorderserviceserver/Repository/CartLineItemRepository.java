package com.service.foodorderserviceserver.Repository;

import com.service.foodorderserviceserver.Entity.CartLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartLineItemRepository extends JpaRepository<CartLineItem, Integer> {
    @Query(value = "select * from cart_line_item i where i.cart_id = :cart_id and i.is_deleted = false", nativeQuery = true)
    List<CartLineItem> findAllByCartId(@Param("cart_id") Integer cartId);
//    List<CartLineItem> findAllByCartId(@Param("cart_id") Integer cartId);
//    List<CartLineItem> findAllByOrderId(Integer orderId);
    @Query(value = "select * from cart_line_item i where i.cart_id = :cart_id and i.variant_product_id = :variant_product_id and i.is_deleted = false", nativeQuery = true)
    Optional<CartLineItem> findByVariantProductIdAndNotDeteted(@Param("cart_id") Integer cartId, @Param("variant_product_id") Integer variantProductId);
}
