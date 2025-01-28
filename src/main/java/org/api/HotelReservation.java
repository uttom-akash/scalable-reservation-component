package org.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages =  {"org.application", "org.api", "org.infrastructure", "org.domain", "org.consumers"})
@EntityScan( basePackages = {"org.domain"} )
@EnableJpaRepositories(basePackages = "org.domain.repositories")
//@EnableSQS
public class HotelReservation {
    public static void main(String[] args) {
        SpringApplication.run(HotelReservation.class);
        System.out.println("Hello, World!");
    }
}