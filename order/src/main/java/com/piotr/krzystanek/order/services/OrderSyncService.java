package com.piotr.krzystanek.order.services;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.OrderSyncRequest;

public interface OrderSyncService {
    void sync(OrderSyncRequest request) throws ApiException;
}
