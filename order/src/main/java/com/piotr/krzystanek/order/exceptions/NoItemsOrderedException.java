package com.piotr.krzystanek.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "provided items are not valid")
public class NoItemsOrderedException extends RuntimeException {
    public NoItemsOrderedException() {
        super("At least one item is mandatory!");
    }
}
