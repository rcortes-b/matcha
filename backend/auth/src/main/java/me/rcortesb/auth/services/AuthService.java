package me.rcortesb.auth.services;

import jakarta.servlet.http.HttpServletRequest;
import me.rcortesb.auth.domain.dto.CredentialsDTO;

public interface AuthService {
    void handleRegister(CredentialsDTO credentialsDTO);
    void handleLogin(CredentialsDTO credentialsDTO);
    void handleLogout();
    void handleCookieRefresh(HttpServletRequest request);
}
