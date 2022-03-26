package com.piotr.krzystanek.orderhistory.browse;

import com.piotr.krzystanek.orderhistory.browse.controllers.OrderHistoryController;
import com.piotr.krzystanek.orderhistory.entities.OrderHistory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderHistoryModelAssembler implements RepresentationModelAssembler<OrderHistory, EntityModel<OrderHistory>> {
    @Override
    public EntityModel<OrderHistory> toModel(OrderHistory entity) {
        return EntityModel.of(entity, linkTo(methodOn(OrderHistoryController.class).getById(entity.getId())).withSelfRel());
    }
}
