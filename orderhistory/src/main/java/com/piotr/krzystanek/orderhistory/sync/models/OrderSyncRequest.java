package com.piotr.krzystanek.orderhistory.sync.models;

import java.math.BigDecimal;
import java.util.List;

public class OrderSyncRequest {
    public Long id;
    public String customerName;
    public String courierName;
    public String deliveryStatus;
    public List<String> products;
    public BigDecimal totalPrice;
}
