package com.piotr.krzystanek.order.persistence;

import com.piotr.krzystanek.order.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class SeedDatabase {
    private static final Logger log = LoggerFactory.getLogger(SeedDatabase.class);

    @Bean
    CommandLineRunner seedProducts(ProductRepository repository) {
        return args -> {
            log.info("Seeding " + repository.save(new Product("Bag of carrots", new BigDecimal(4))));
            log.info("Seeding " + repository.save(new Product("Bag of potatoes", new BigDecimal(3))));
            log.info("Seeding " + repository.save(new Product("Bag of beetroots", new BigDecimal(4.15))));
        };
    }
}
