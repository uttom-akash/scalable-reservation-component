package org.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.application", "org.api","org.infrastructure", "org.domain"})
public class HotelBooking {
    public static void main(String[] args) {
        SpringApplication.run(HotelBooking.class);
        System.out.println("Hello, World!");
    }
}