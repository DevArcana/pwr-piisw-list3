package com.piotr.krzystanek.order.rest;

import com.piotr.krzystanek.order.entities.Order;
import com.piotr.krzystanek.order.entities.OrderItem;
import com.piotr.krzystanek.order.exceptions.NoItemsOrderedException;
import com.piotr.krzystanek.order.persistence.ProductRepository;
import com.piotr.krzystanek.order.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;
    private final ProductRepository productRepository;

    public OrdersController(OrderService orderService, ProductRepository productRepository) {
        this.orderService = orderService;
        this.productRepository = productRepository;
    }

    @PostMapping
    Order createOrder(@RequestBody CreateOrderRequest request) {
        var products = productRepository.findAllById(request.items.stream().map(item -> item.productId).toList());

        if (products.size() != request.items.size()) {
            throw new NoItemsOrderedException();
        }

        var items = IntStream.range(0, products.size()).mapToObj(i -> new OrderItem(products.get(i), request.items.get(i).quantity)).toList();

        return orderService.createOrder(request.customerName, items);
    }

    @PostMapping("/{id}/delivery")
    Order createDelivery(@PathVariable Long id, @RequestBody CreateDeliveryRequest request) {
        return orderService.createDelivery(id, request.courierName);
    }

    @PutMapping("/{id}/delivery")
    Order updateDeliveryStatus(@PathVariable Long id, @RequestBody UpdateDeliveryRequest request) {
        return orderService.updateDelivery(id, request.status);
    }
}
