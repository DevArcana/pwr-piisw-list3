package com.piotr.krzystanek.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "delivery already exists")
public class DeliveryAlreadyExistsException extends RuntimeException {
    public DeliveryAlreadyExistsException(Long orderId) {
        super("Order " + orderId + " already has a delivery scheduled!");
    }
}
