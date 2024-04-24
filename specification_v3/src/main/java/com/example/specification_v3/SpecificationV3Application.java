package com.example.specification_v3;

import com.example.specification_v3.entities.Coffee;
import com.example.specification_v3.repositories.CoffeeRepository;
import com.example.specification_v3.services.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class SpecificationV3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpecificationV3Application.class, args);
    }

    private final CoffeeRepository coffeeRepository;
    private final CoffeeService coffeeService;

    @Bean
    CommandLineRunner run() {
        return args -> {
            // Get Coffee by ID
            System.out.println("Get Coffee By ID = 10");
            System.out.println(coffeeService.getCoffeeById(3));
            System.out.println("============================");

            // Get Coffee by Name Like and Type
            System.out.println("Get Coffee by Type = ICE");
            System.out.println(coffeeService.getCoffeeByComplexCondition("name-%", Coffee.CoffeeType.ICE));
            System.out.println("============================");
        };
    }

}
