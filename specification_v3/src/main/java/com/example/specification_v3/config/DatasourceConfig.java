package com.example.specification_v3.config;

import com.example.specification_v3.entities.Coffee;
import com.example.specification_v3.repositories.CoffeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@RequiredArgsConstructor
public class DatasourceConfig {
    private final CoffeeRepository coffeeRepository;

    @PostConstruct
    public void init() {
        final Random random = new Random();
        coffeeRepository.saveAll(IntStream.range(0, 100)
                .mapToObj(i -> Coffee.builder()
                        .name("name-" + i)
                        .type(random.nextDouble() >= 0.5 ? Coffee.CoffeeType.HOT : Coffee.CoffeeType.ICE)
                        .build())
                .collect(Collectors.toList()));
    }
}
