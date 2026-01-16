package me.rcortesb.auth.services;

import jakarta.servlet.http.HttpServletRequest;
import me.rcortesb.auth.domain.dto.LoginUserDTO;
import me.rcortesb.auth.domain.dto.RegisterUserDTO;

public interface AuthService {
    void handleRegister(RegisterUserDTO registerUserDTO);
    void handleLogin(LoginUserDTO loginUserDTO);
    void handleLogout();
    void handleCookieRefresh(HttpServletRequest request);
}
