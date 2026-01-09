package me.rcortesb.gateway.circuit_breaker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class CircuitBreakerController {
    @RequestMapping("/default")
    public Mono<String> fallback() {
        return Mono.just("Service is currently unavailable. Please try later.");
    }

}
