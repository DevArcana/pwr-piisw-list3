package com.piotr.krzystanek.orderhistory;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public GroupedOpenApi WriteOpenApi() {
        return GroupedOpenApi.builder().group("sync").packagesToScan("com.piotr.krzystanek.orderhistory.sync").build();
    }

    @Bean
    public GroupedOpenApi ReadOpenApi() {
        return GroupedOpenApi.builder().group("browse").packagesToScan("com.piotr.krzystanek.orderhistory.browse").build();
    }
}