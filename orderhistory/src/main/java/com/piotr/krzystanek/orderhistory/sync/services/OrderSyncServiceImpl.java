package com.piotr.krzystanek.orderhistory.sync.services;

import com.piotr.krzystanek.orderhistory.entities.OrderHistory;
import com.piotr.krzystanek.orderhistory.sync.persistence.OrderHistoryRepository;
import com.piotr.krzystanek.orderhistory.sync.models.OrderSyncRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderSyncServiceImpl implements OrderSyncService {
    private final OrderHistoryRepository repository;

    public OrderSyncServiceImpl(OrderHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderHistory sync(OrderSyncRequest request) {
        var existing = repository.findById(request.id);
        OrderHistory history = existing.orElseGet(OrderHistory::new);

        history.setId(request.id);
        history.setCustomerName(request.customerName);
        history.setCourierName(request.courierName);
        history.setDeliveryStatus(request.deliveryStatus);
        history.setTotalPrice(request.totalPrice);
        history.setProductNames(String.join(", ", request.products));

        return repository.save(history);
    }
}
