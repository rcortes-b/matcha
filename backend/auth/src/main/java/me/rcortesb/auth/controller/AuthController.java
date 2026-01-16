package me.rcortesb.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import me.rcortesb.auth.domain.dto.LoginUserDTO;
import me.rcortesb.auth.domain.dto.RegisterUserDTO;
import me.rcortesb.auth.services.AuthService;
import me.rcortesb.auth.services.VerificationService;
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
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterUserDTO registerUserDTO) {
        LOGGER.info("[CONTROLLER - REGISTER]: ARRIVED");
        authService.handleRegister(registerUserDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginUserDTO loginUserDTO) {
        try {
            authService.handleLogin(loginUserDTO);
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
            verificationService.sendEmail(loginUserDTO.email(), code);
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

    @PostMapping("/cookie-refresh")
    public ResponseEntity<Void> refreshCookie(HttpServletRequest request) {
        authService.handleCookieRefresh(request);
        return ResponseEntity.ok().build();
    }
}
