package com.piotr.krzystanek.order.entities;

import com.piotr.krzystanek.order.exceptions.InvalidDeliveryStatusTransitionException;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String courierName;

    @Column(nullable = false)
    private DeliveryStatus status;

    public Delivery(String courierName) {
        this.courierName = courierName;
        this.status = DeliveryStatus.Created;
    }

    public void updateStatus(DeliveryStatus status) {
        if (this.status == status) {
            return;
        }

        if (this.status == DeliveryStatus.Created && status == DeliveryStatus.PickedUp) {
            this.status = status;
        }
        else if (this.status == DeliveryStatus.PickedUp && status == DeliveryStatus.Delivered) {
            this.status = status;
        }
        else {
            throw new InvalidDeliveryStatusTransitionException(this, status);
        }
    }
}
