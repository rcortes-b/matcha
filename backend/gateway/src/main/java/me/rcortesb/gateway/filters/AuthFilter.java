package me.rcortesb.gateway.filters;

import me.rcortesb.gateway.grpc.JwtTokenClient;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GatewayFilter {
    private final Logger logger = Logger.getLogger(AuthFilter.class);
    @Value("${security.secret-header.key}")
    private String secretHeader;
    @Autowired
    private JwtTokenClient jwtTokenClient;
    @Value("${security.cookie.name}")
    private String cookieName;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        final HttpCookie cookie = request.getCookies().getFirst(cookieName);
        if (cookie == null)
            throw new RuntimeException("cookie not found");
        System.out.println("cookie value: " + cookie.getValue());
        String userId = jwtTokenClient.fetchJwtToken(cookie.getValue());
        System.out.println("userId value: " + userId);
        logger.info("[gRPC FETCHING] userId value: " + userId);

        return chain.filter(exchange.mutate()
                .request(r -> r
                        .header("X-User-Id", userId)
                        .header("X-Gateway-Secret", secretHeader))
                .build()
        );

    }

}
