package me.rcortesb.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import me.rcortesb.auth.domain.dto.CredentialsDTO;
import me.rcortesb.auth.services.AuthService;
import me.rcortesb.auth.services.VerificationService;
import me.rcortesb.auth.services.impl.VerificationServiceImpl;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    final private static Logger LOGGER = Logger.getLogger(AuthController.class);
    final private AuthService authService;
    final private VerificationService verificationService;

    public AuthController(AuthService authService, VerificationService verificationService) {
        this.authService = authService;
        this.verificationService = verificationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid CredentialsDTO credentialsDTO) {
        LOGGER.info("[CONTROLLER - REGISTER]: ARRIVED");
        authService.handleRegister(credentialsDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody CredentialsDTO credentialsDTO) {
        try {
            authService.handleLogin(credentialsDTO);
            return ResponseEntity.ok(Map.of(
                            "code", "SUCCESS",
                            "message", "Login has been successful"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "code", "INVALID_CREDENTIALS",
                            "message", "Username or password is incorrect"));
        } catch (DisabledException e) {
            final String code = verificationService.generateVerificationCode();
            verificationService.sendEmail(credentialsDTO.email(), code);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "code", "USER_NOT_VERIFIED",
                            "message", "The user is not verified"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authService.handleLogout();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth/cookie-refresh")
    public ResponseEntity<Void> refreshCookie() {
        authService.handleCookieRefresh();
        return ResponseEntity.ok().build();
    }
}
