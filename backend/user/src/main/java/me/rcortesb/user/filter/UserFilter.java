package me.rcortesb.user.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;

import java.io.IOException;

@Component
public class UserFilter extends OncePerRequestFilter {
    private final Logger logger = Logger.getLogger(UserFilter.class);
    @Value("${security.secret-header.value}")
    private String secretHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String secret = request.getHeader("X-Gateway-Secret");

        if (!secretHeader.equals(secret)) {
            // This is an unauthorized request!
            throw new RuntimeException("X-Gateway-Secret header is incorrect: " + secret + "\nCurrent header: " + secretHeader);
        }
        System.out.println("X-Gateway-Secret header is correct: " + secret);
        filterChain.doFilter(request, response);
    }
}
