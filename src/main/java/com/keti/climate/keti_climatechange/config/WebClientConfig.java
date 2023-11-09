package com.keti.climate.keti_climatechange.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Configuration
public class WebClientConfig {
    private static final Logger logger = LogManager.getLogger();


    String baseUrl ="";
    String origin ="";
    String header ="";

    @Bean
    public WebClient webClient() {
        logger.info("WebClient initialize...");
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1))
                .build();

        return WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .baseUrl(Objects.requireNonNull(baseUrl))
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("Accept", "application/json");
                    httpHeaders.add("X-M2M-Origin", origin);
                    httpHeaders.add("X-M2M-RI", header);
                })
                .build();
    }

}
