package com.piotr.krzystanek.order.services;

import com.piotr.krzystanek.order.entities.Delivery;
import com.piotr.krzystanek.order.entities.DeliveryStatus;
import com.piotr.krzystanek.order.entities.Order;
import com.piotr.krzystanek.order.entities.OrderItem;
import com.piotr.krzystanek.order.exceptions.DeliveryAlreadyExistsException;
import com.piotr.krzystanek.order.exceptions.NoDeliveryException;
import com.piotr.krzystanek.order.exceptions.OrderNotFoundException;
import com.piotr.krzystanek.order.persistence.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order createOrder(String customerName, List<OrderItem> items) {
        return repository.save(new Order(customerName, items));
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

        return repository.save(order);
    }
}
