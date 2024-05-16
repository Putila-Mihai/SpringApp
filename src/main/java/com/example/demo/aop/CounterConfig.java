package com.example.demo.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterConfig {

    @Bean
    public TrackCounter counter() {
        return new TrackCounter();
    }
}
