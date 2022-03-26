package com.piotr.krzystanek.orderhistory.browse.persistence;

import com.piotr.krzystanek.orderhistory.entities.OrderHistory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderHistoryPagedRepository extends PagingAndSortingRepository<OrderHistory, Long> {
}
