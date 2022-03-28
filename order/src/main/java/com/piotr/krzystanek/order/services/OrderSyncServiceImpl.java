package com.piotr.krzystanek.order.services;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.SyncControllerApi;
import org.openapitools.client.model.OrderSyncRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderSyncServiceImpl implements OrderSyncService {

    private final SyncControllerApi orderHistory = new SyncControllerApi();

    @Override
    public void sync(OrderSyncRequest request) throws ApiException {
        orderHistory.sync(request);
    }
}
