package com.piotr.krzystanek.orderhistory.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {
    @Id
    private Long id;

    @Column(nullable = false)
    private String customerName;

    private String courierName;
    private String deliveryStatus;

    @Column(nullable = false)
    private String productNames;

    @Column(nullable = false)
    private BigDecimal totalPrice;
}
