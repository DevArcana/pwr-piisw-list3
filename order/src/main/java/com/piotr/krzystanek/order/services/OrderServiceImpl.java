package com.piotr.krzystanek.order.services;

import com.piotr.krzystanek.order.entities.Delivery;
import com.piotr.krzystanek.order.entities.DeliveryStatus;
import com.piotr.krzystanek.order.entities.Order;
import com.piotr.krzystanek.order.entities.OrderItem;
import com.piotr.krzystanek.order.exceptions.DeliveryAlreadyExistsException;
import com.piotr.krzystanek.order.exceptions.NoDeliveryException;
import com.piotr.krzystanek.order.exceptions.OrderNotFoundException;
import com.piotr.krzystanek.order.persistence.OrderRepository;
import org.openapitools.client.model.OrderSyncRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    private final OrderSyncService orderSyncService;

    public OrderServiceImpl(OrderRepository repository, OrderSyncService orderSyncService) {
        this.repository = repository;
        this.orderSyncService = orderSyncService;
    }

    private void syncOrder(Order order) {
        var request = new OrderSyncRequest();

        request.setId(order.getId());
        request.setCustomerName(order.getCustomerName());

        var delivery = order.getDelivery();
        if (delivery != null) {
            request.setCourierName(delivery.getCourierName());
            request.setDeliveryStatus(delivery.getStatus().name());
        }

        request.setTotalPrice(order.getTotalPrice());
        request.setProducts(order.getItems().stream().map(orderItem -> orderItem.getProduct().getName()).toList());

        try {
            orderSyncService.sync(request);
        }
        catch (Exception exception) {
            // possibly log error
        }
    }

    @Override
    public Order createOrder(String customerName, List<OrderItem> items) {
        var order = repository.save(new Order(customerName, items));
        syncOrder(order);
        return order;
    }

    @Override
    public Order createDelivery(Long orderId, String courierName) {
        var result = repository.findById(orderId);

        if (result.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }

        var order = result.get();
        if (order.getDelivery() != null) {
            throw new DeliveryAlreadyExistsException(orderId);
        }

        order.setDelivery(new Delivery(courierName));

        syncOrder(order);
        return repository.save(order);
    }

    @Override
    public Order updateDelivery(Long orderId, DeliveryStatus status) {
        var result = repository.findById(orderId);

        if (result.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }

        var order = result.get();
        var delivery = order.getDelivery();
        if (delivery == null) {
            throw new NoDeliveryException(orderId);
        }

        delivery.updateStatus(status);

        syncOrder(order);
        return repository.save(order);
    }
}
