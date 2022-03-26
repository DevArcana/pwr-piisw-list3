package com.piotr.krzystanek.order.entities;

import com.piotr.krzystanek.order.exceptions.InvalidDeliveryStatusTransitionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeliveryTests {
    @Test
    void startsAtCreated() {
        var delivery = new Delivery("courier");
        assert delivery.getStatus() == DeliveryStatus.Created;
    }

    @Test
    void canChangeStatusInOrder() {
        var delivery = new Delivery("courier");

        delivery.updateStatus(DeliveryStatus.PickedUp);
        assert delivery.getStatus() == DeliveryStatus.PickedUp;

        delivery.updateStatus(DeliveryStatus.Delivered);
        assert delivery.getStatus() == DeliveryStatus.Delivered;
    }

    @Test
    void throwsOnInvalidStateChange() {
        var delivery = new Delivery("courier");

        delivery.updateStatus(DeliveryStatus.PickedUp);

        Assertions.assertThrows(InvalidDeliveryStatusTransitionException.class, () -> {
            delivery.updateStatus(DeliveryStatus.Created);
        });

        delivery.updateStatus(DeliveryStatus.Delivered);

        Assertions.assertThrows(InvalidDeliveryStatusTransitionException.class, () -> {
            delivery.updateStatus(DeliveryStatus.Created);
        });

        Assertions.assertThrows(InvalidDeliveryStatusTransitionException.class, () -> {
            delivery.updateStatus(DeliveryStatus.PickedUp);
        });
    }
}
