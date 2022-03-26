package com.piotr.krzystanek.order.entities;

import com.piotr.krzystanek.order.exceptions.NoItemsOrderedException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String customerName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    @OneToOne(cascade = CascadeType.ALL)
    private Delivery delivery;

    public Order(String customerName, List<OrderItem> items) {
        if (items == null || items.size() == 0) {
            throw new NoItemsOrderedException();
        }

        this.customerName = customerName;
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return items.stream().map(OrderItem::getPrice).reduce(new BigDecimal(0), BigDecimal::add);
    }
}
