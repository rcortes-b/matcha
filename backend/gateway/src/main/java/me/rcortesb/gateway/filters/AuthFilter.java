package me.rcortesb.gateway.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.Cookie;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
public class AuthFilter {}
/*@Component
public class AuthFilter implements GatewayFilter {
    private final JwtDecoder jwtDecoder;
    @Value("{security.cookie.name}")
    private String cookieName;

    public AuthFilter(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        final HttpCookie cookie = request.getCookies().getFirst(cookieName);
        if (cookie == null)
            throw new RuntimeException("cookie not found");
        System.out.println("cookie value: " + cookie.getValue());
        String userId = extractValueFromJwt(cookie.getValue());
        System.out.println("userId value: " + userId);

        //request mutate headers ...


        return null;
    }

    private String extractValueFromJwt(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }
}*/
