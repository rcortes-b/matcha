package me.rcortesb.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    WebClient webClient() {
        return WebClient.builder()
                        .baseUrl("https://nominatim.openstreetmap.org")
                        .defaultHeader("User-Agent", "MatchaApp")
                        .build();
    }
}
