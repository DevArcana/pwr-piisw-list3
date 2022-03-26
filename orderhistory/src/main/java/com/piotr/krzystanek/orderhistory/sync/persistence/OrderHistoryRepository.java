package com.piotr.krzystanek.orderhistory.sync.persistence;

import com.piotr.krzystanek.orderhistory.entities.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}
