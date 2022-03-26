package com.piotr.krzystanek.order.exceptions;

import com.piotr.krzystanek.order.entities.Delivery;
import com.piotr.krzystanek.order.entities.DeliveryStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDeliveryStatusTransitionException extends RuntimeException {
    public InvalidDeliveryStatusTransitionException(Delivery delivery, DeliveryStatus status) {
        super("Cannot transition delivery " + delivery.getId() + " from status " + delivery.getStatus() + " to " + status);
    }
}
