package me.rcortesb.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import me.rcortesb.auth.domain.dto.CredentialsDTO;
import me.rcortesb.auth.services.AuthService;
import org.jboss.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    final private static Logger LOGGER = Logger.getLogger(AuthController.class);
    final private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Void> register(@RequestBody @Valid CredentialsDTO credentialsDTO) {
        LOGGER.info("[CONTROLLER - REGISTER]: ARRIVED");
        authService.handleRegister(credentialsDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Void> login(@RequestBody CredentialsDTO credentialsDTO) {
        authService.handleLogin(credentialsDTO);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> logout() {
        authService.handleLogout();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/cookie-refresh")
    public ResponseEntity<Void> refreshCookie() {
        return ResponseEntity.ok().build();
    }
}
