package me.rcortesb.auth.services;

import me.rcortesb.auth.domain.dto.CredentialsDTO;

public interface AuthService {
    void handleRegister(CredentialsDTO credentialsDTO);
    void handleLogin(CredentialsDTO credentialsDTO);
    void handleLogout();

}
