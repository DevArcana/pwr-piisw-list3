package com.piotr.krzystanek.order.entities;

import com.piotr.krzystanek.order.exceptions.InvalidQuantityException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orderitem")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;

    private int quantity;

    public OrderItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException(quantity);
        }

        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}
