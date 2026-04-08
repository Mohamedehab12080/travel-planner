package com.fawry.external.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ExternalApiConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .defaultHeader("User-Agent", "Travel-Planner-Application")
                .build();
    }
}