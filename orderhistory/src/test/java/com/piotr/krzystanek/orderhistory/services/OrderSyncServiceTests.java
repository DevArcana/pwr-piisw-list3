package com.piotr.krzystanek.orderhistory.services;

import com.piotr.krzystanek.orderhistory.entities.OrderHistory;
import com.piotr.krzystanek.orderhistory.sync.models.OrderSyncRequest;
import com.piotr.krzystanek.orderhistory.sync.persistence.OrderHistoryRepository;
import com.piotr.krzystanek.orderhistory.sync.services.OrderSyncServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class OrderSyncServiceTests {

    OrderHistoryRepository repository = Mockito.mock(OrderHistoryRepository.class);

    @Test
    void updatesExistingObject() {
        var sut = new OrderSyncServiceImpl(repository);
        var obj = new OrderHistory();
        obj.setId(17L);
        obj.setProductNames("Apple, Bananas");
        obj.setDeliveryStatus("PickedUp");
        obj.setCourierName("John");

        var request = new OrderSyncRequest();
        request.id = 17L;
        request.courierName = "John";
        request.customerName = "Bob";
        request.deliveryStatus = "Delivered";
        request.totalPrice = new BigDecimal(17);
        request.products = new ArrayList<>();
        request.products.add("Apple");
        request.products.add("Bananas");

        Mockito.when(repository.findById(17L)).thenReturn(Optional.of(obj));
        Mockito.when(repository.save(Mockito.any())).thenAnswer(new Answer<OrderHistory>() {
            @Override
            public OrderHistory answer(InvocationOnMock invocationOnMock) throws Throwable {
                return invocationOnMock.getArgument(0);
            }
        });

        var result = sut.sync(request);

        assert result == obj;
    }

    @Test
    void updatesDeliveryStatus() {
        var sut = new OrderSyncServiceImpl(repository);
        var obj = new OrderHistory();
        obj.setId(17L);
        obj.setProductNames("Apple, Bananas");
        obj.setDeliveryStatus("PickedUp");
        obj.setCourierName("John");

        var request = new OrderSyncRequest();
        request.id = 17L;
        request.courierName = "John";
        request.customerName = "Bob";
        request.deliveryStatus = "Delivered";
        request.totalPrice = new BigDecimal(17);
        request.products = new ArrayList<>();
        request.products.add("Apple");
        request.products.add("Bananas");

        Mockito.when(repository.findById(17L)).thenReturn(Optional.of(obj));
        Mockito.when(repository.save(Mockito.any())).thenAnswer(new Answer<OrderHistory>() {
            @Override
            public OrderHistory answer(InvocationOnMock invocationOnMock) throws Throwable {
                return invocationOnMock.getArgument(0);
            }
        });

        var result = sut.sync(request);

        assert result.getDeliveryStatus() == "Delivered";
    }
}
