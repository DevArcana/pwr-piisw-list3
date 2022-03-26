package com.piotr.krzystanek.order.persistence;

import com.piotr.krzystanek.order.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
