package com.service.foodorderserviceserver.Entity;

import com.service.foodorderserviceserver.Entity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Total_Price")
    private Double totalPrice;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "cartId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartLineItem> cartLineItems = new ArrayList<>();

    // Add a cart line item
    public void addCartLineItem(CartLineItem cartLineItem) {
        cartLineItems.add(cartLineItem);
        cartLineItem.setCartId(this);
    }

    // Remove a cart line item
    public void removeCartLineItem(CartLineItem cartLineItem) {
        cartLineItems.remove(cartLineItem);
        cartLineItem.setCartId(null);
    }

    // Clear all cart line items
    public void clearCartLineItems() {
        for (CartLineItem cartLineItem : cartLineItems) {
            cartLineItem.setCartId(null);
        }
        cartLineItems.clear();
    }

    public Integer numberOfCartLineItems() {
        return cartLineItems.size();
    }

    // Calculate the total price of the cart
    public void calculateTotalPrice() {
        double total = 0;
        for (CartLineItem cartLineItem : cartLineItems) {
            total += cartLineItem.getTotalPrice();
        }
        this.totalPrice = total;
    }

    // Update the quantity of a cart line item
    public void updateCartLineItemQuantity(CartLineItem cartLineItem, int quantity) {
        cartLineItem.setQuantity(quantity);
        cartLineItem.setTotalPrice(cartLineItem.getProductId().getPrice() * quantity);
    }


}
