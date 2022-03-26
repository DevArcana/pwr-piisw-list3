package com.piotr.krzystanek.order.rest;

import java.util.List;

public class CreateOrderRequest {
    public String customerName;
    public List<CreateOrderRequestOrderItem> items;
}
