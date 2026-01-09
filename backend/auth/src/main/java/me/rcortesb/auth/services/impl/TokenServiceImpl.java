package me.rcortesb.auth.services.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import me.rcortesb.auth.domain.common.CookieProperties;
import me.rcortesb.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${application.security.jwt.expiration}")
    private int jwtExpiration;
    @Value("${application.security.jwt.expiration-refresh}")
    private int jwtExpirationRefresh;

    final private JwtEncoder jwtEncoder;
    final private JwtDecoder jwtDecoder;
    final private CookieProperties cookieProperties;

    public TokenServiceImpl(JwtEncoder jwtEncoder, CookieProperties cookieProperties,
                            JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.cookieProperties = cookieProperties;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public String generateToken(String subject, boolean isRefresh) {
        final Instant issuedAt = Instant.now();
        final Instant expiresAt =  isRefresh ? issuedAt.plus(jwtExpirationRefresh, ChronoUnit.DAYS)
                                             : issuedAt.plus(jwtExpiration, ChronoUnit.MINUTES);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .build();
        var jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    public String getSubjectFromRefreshCookie(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        final Optional<String> token = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieProperties.getNameRefresh()))
                .map(Cookie::getValue)
                .findFirst();
        if (token.isEmpty())
            return null;
        return getSubjectFromJwt(token.get());
    }

    private String getSubjectFromJwt(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        if (jwt == null)
            return null;
        return jwt.getSubject();
    }

}
