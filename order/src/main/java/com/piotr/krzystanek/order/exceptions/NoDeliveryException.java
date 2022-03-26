package com.piotr.krzystanek.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoDeliveryException extends RuntimeException {
    public NoDeliveryException(Long orderId) {
        super("Order " + orderId + " does not have a delivery scheduled!");
    }
}
