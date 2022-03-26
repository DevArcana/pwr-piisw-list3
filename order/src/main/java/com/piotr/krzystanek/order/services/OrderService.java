package com.piotr.krzystanek.order.services;

import com.piotr.krzystanek.order.entities.DeliveryStatus;
import com.piotr.krzystanek.order.entities.Order;
import com.piotr.krzystanek.order.entities.OrderItem;

import java.util.List;

public interface OrderService {
    Order createOrder(String customerName, List<OrderItem> items);
    Order createDelivery(Long orderId, String courierName);
    Order updateDelivery(Long orderId, DeliveryStatus status);
}
