package com.piotr.krzystanek.order.persistence;

import com.piotr.krzystanek.order.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
