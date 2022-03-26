package com.piotr.krzystanek.orderhistory.browse.controllers;

import com.piotr.krzystanek.orderhistory.browse.OrderHistoryModelAssembler;
import com.piotr.krzystanek.orderhistory.browse.persistence.OrderHistoryPagedRepository;
import com.piotr.krzystanek.orderhistory.entities.OrderHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordershistory")
public class OrderHistoryController {
    private final OrderHistoryPagedRepository repository;

    private final OrderHistoryModelAssembler orderHistoryModelAssembler;

    private final PagedResourcesAssembler pagedResourcesAssembler;

    public OrderHistoryController(OrderHistoryPagedRepository repository, OrderHistoryModelAssembler orderHistoryModelAssembler, PagedResourcesAssembler pagedResourcesAssembler) {
        this.repository = repository;
        this.orderHistoryModelAssembler = orderHistoryModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<OrderHistory>> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(orderHistory -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(orderHistoryModelAssembler.toModel(orderHistory)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public PagedModel<EntityModel<OrderHistory>> getAll(Pageable pageable) {
        return pagedResourcesAssembler.toModel(repository.findAll(pageable), orderHistoryModelAssembler);
    }
}
