package com.piotr.krzystanek.order.services;

import com.piotr.krzystanek.order.entities.*;
import com.piotr.krzystanek.order.exceptions.OrderNotFoundException;
import com.piotr.krzystanek.order.persistence.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class OrderServiceImplTests {
    OrderRepository repository = Mockito.mock(OrderRepository.class);
    OrderSyncService orderSyncService = Mockito.mock(OrderSyncService.class);

    @Test
    void createOrder() {
        var sut = new OrderServiceImpl(repository, orderSyncService);
        var apple = new Product("apple", new BigDecimal(2));
        var banana = new Product("apple", new BigDecimal(3));
        var items = new ArrayList<OrderItem>();
        items.add(new OrderItem(apple, 3));
        items.add(new OrderItem(banana, 2));

        Mockito.when(repository.save(Mockito.any())).thenAnswer((Answer<Order>) invocationOnMock -> invocationOnMock.getArgument(0));

        var order = sut.createOrder("John", items);

        assert order.getDelivery() == null;
        assert order.getCustomerName().equals("John");
        assert order.getTotalPrice().equals(new BigDecimal(12));
        Assertions.assertArrayEquals(items.toArray(), order.getItems().toArray());
    }

    @Test
    void createDeliveryForOrder() {
        var sut = new OrderServiceImpl(repository, orderSyncService);
        var apple = new Product("apple", new BigDecimal(2));
        var banana = new Product("apple", new BigDecimal(3));
        var items = new ArrayList<OrderItem>();
        items.add(new OrderItem(apple, 3));
        items.add(new OrderItem(banana, 2));
        var order = new Order("John", items);
        order.setId(17L);
        Mockito.when(repository.findById(17L)).thenReturn(Optional.of(order));
        Mockito.when(repository.save(Mockito.any())).thenAnswer((Answer<Order>) invocationOnMock -> invocationOnMock.getArgument(0));

        order = sut.createDelivery(17L, "Bob");
        var delivery = order.getDelivery();
        assert delivery.getCourierName().equals("Bob");
        assert delivery.getStatus() == DeliveryStatus.Created;
    }

    @Test
    void throwsWhenDeliveryCreatedForNonExistingOrder() {
        var sut = new OrderServiceImpl(repository, orderSyncService);
        Mockito.when(repository.findById(17L)).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any())).thenAnswer((Answer<Order>) invocationOnMock -> invocationOnMock.getArgument(0));

        Assertions.assertThrows(OrderNotFoundException.class, () -> sut.createDelivery(17L, "Bob"));
    }

    @Test
    void updateDeliveryStatus() {
        var sut = new OrderServiceImpl(repository, orderSyncService);
        var apple = new Product("apple", new BigDecimal(2));
        var banana = new Product("apple", new BigDecimal(3));
        var items = new ArrayList<OrderItem>();
        items.add(new OrderItem(apple, 3));
        items.add(new OrderItem(banana, 2));
        var delivery = new Delivery("Bob");
        var order = new Order("John", items);
        order.setId(17L);
        order.setDelivery(delivery);
        Mockito.when(repository.findById(17L)).thenReturn(Optional.of(order));
        Mockito.when(repository.save(Mockito.any())).thenAnswer((Answer<Order>) invocationOnMock -> invocationOnMock.getArgument(0));

        order = sut.updateDelivery(17L, DeliveryStatus.PickedUp);
        assert order.getDelivery().getStatus() == DeliveryStatus.PickedUp;
    }
}
