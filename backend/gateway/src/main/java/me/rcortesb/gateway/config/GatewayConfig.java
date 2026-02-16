package me.rcortesb.gateway.config;

import me.rcortesb.gateway.filters.AuthFilter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.List;

/*
    https://spring.io/guides/gs/gateway#initial
    https://medium.com/@xuezhongyu01/filter-of-spring-cloud-gateway-359959343c87
*/

@Configuration
public class GatewayConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000", "static://matcha-frontend:3030")); // frontend origin
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, RedisRateLimiter redisRateLimiter,
                                KeyResolver keyResolver, AuthFilter authFilter) {
        return builder.routes()
                .route(p -> p
                        .path("/api/auth/**")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("authServiceCB")
                                        .setFallbackUri("forward:/fallback/default"))
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter)
                                        .setKeyResolver(keyResolver))
                        )
                        .uri("http://matcha-auth-service-dev:8081")
                )
                .route(p -> p
                        .path("/api/users/**")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("defaultServiceCB")
                                        .setFallbackUri("forward:/fallback/default"))
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter)
                                        .setKeyResolver(keyResolver))
                                .filter(authFilter)
                        )
                        .uri("http://matcha-user-service-dev:8082")
                )
				.route(p -> p
                        .path("/api/browse/**")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("defaultServiceCB")
                                        .setFallbackUri("forward:/fallback/default"))
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter)
                                        .setKeyResolver(keyResolver))
                                .filter(authFilter)
                        )
                        .uri("http://matcha-browse-service-dev:8083")
                )
                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(10, 20);
    }

    @Bean
    public KeyResolver keyResolver() {
        return exchange -> {
            var addr = exchange.getRequest().getRemoteAddress();
            if (addr == null || addr.getAddress() == null) {
                return Mono.just("default");
            }
            return Mono.just(addr.getAddress().getHostAddress());
        };
    }
}
