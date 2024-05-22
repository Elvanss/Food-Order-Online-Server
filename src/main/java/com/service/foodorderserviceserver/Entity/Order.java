package com.service.foodorderserviceserver.Entity;

import com.service.foodorderserviceserver.Entity.Type.OrderStatus;
import com.service.foodorderserviceserver.Entity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`orderx`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant", referencedColumnName = "id")
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Column(name = "total-price")
    private Double totalPrice;

    @Column(name = "order-date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    public Integer getNumberOfOrderLineItems() {
        return orderLineItems.size();
    }

    public void addOrderLineItem(OrderLineItem orderLineItem) {
        this.orderLineItems.add(orderLineItem);
        orderLineItem.setOrder(this);
    }

    public void removeOrderLineItem(OrderLineItem orderLineItem) {
        this.orderLineItems.remove(orderLineItem);
        orderLineItem.setOrder(null);
    }

    public void removeAllOrderLineItems() {
        this.orderLineItems.clear();
    }

}
