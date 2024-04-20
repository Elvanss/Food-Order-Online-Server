package com.service.foodorderserviceserver;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FoodOrderServiceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderServiceServerApplication.class, args);
    }

}
