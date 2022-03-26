package com.piotr.krzystanek.orderhistory.sync.services;

import com.piotr.krzystanek.orderhistory.entities.OrderHistory;
import com.piotr.krzystanek.orderhistory.sync.models.OrderSyncRequest;

public interface OrderSyncService {
    OrderHistory sync(OrderSyncRequest request);
}
