package com.piotr.krzystanek.orderhistory.sync.controllers;

import com.piotr.krzystanek.orderhistory.entities.OrderHistory;
import com.piotr.krzystanek.orderhistory.sync.models.OrderSyncRequest;
import com.piotr.krzystanek.orderhistory.sync.services.OrderSyncService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordershistory")
public class SyncController {
    private final OrderSyncService orderSyncService;

    public SyncController(OrderSyncService orderSyncService) {
        this.orderSyncService = orderSyncService;
    }

    @PostMapping
    OrderHistory sync(@RequestBody OrderSyncRequest request) {
        return orderSyncService.sync(request);
    }
}
