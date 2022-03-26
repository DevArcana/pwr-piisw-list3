package com.piotr.krzystanek.order.entities;

import com.piotr.krzystanek.order.exceptions.InvalidQuantityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OrderItemTests {
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 0})
    void throwOnInvalidQuantity(int quantity) {
        Assertions.assertThrows(InvalidQuantityException.class, () -> new OrderItem(new Product("carrot", new BigDecimal(4)), quantity));
    }

    @Test
    void calculatesPrice() {
        var product = new Product("carrot", new BigDecimal(4));
        var orderItem = new OrderItem(product, 3);

        assert orderItem.getPrice().equals(new BigDecimal(12));
    }
}
