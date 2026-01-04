package me.rcortesb.auth.services.impl;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import me.rcortesb.auth.domain.entity.UserSecurity;
import me.rcortesb.auth.services.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${application.security.jwt.expiration}")
    private int jwtExpiration;

    final private JwtEncoder jwtEncoder;

    public TokenServiceImpl(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public String generateToken(Authentication authentication, boolean isRefresh) {
        final Instant now = Instant.now();
        final UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        final String subject = isRefresh ? userSecurity.getUsername() : userSecurity.getId().toString();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plus(jwtExpiration, ChronoUnit.MINUTES))
                .build();
        var jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

}
