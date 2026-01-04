package me.rcortesb.auth.services;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String generateToken(Authentication authentication, boolean isRefresh);
}
