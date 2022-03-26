package com.piotr.krzystanek.order.entities;

import com.piotr.krzystanek.order.exceptions.NoItemsOrderedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class OrderTests {
    @Test
    void throwWhenZeroItemsOrdered() {
        Assertions.assertThrows(NoItemsOrderedException.class, () -> new Order("customer name", null));
        Assertions.assertThrows(NoItemsOrderedException.class, () -> new Order("customer name", new ArrayList<>()));
    }

    @Test
    void calculatesTotalPrice() {
        var product1 = new Product("carrot", new BigDecimal(4));
        var product2 = new Product("potato", new BigDecimal(3));

        var order = new Order("customer name", Arrays.asList(new OrderItem(product1, 2), new OrderItem(product2, 3)));

        assert order.getTotalPrice().equals(new BigDecimal(17));
    }
}
