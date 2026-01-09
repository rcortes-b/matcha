package me.rcortesb.auth.exceptions;

import me.rcortesb.auth.exceptions.verification.CookieNotFoundException;
import me.rcortesb.auth.exceptions.verification.InvalidVerificationCodeException;
import me.rcortesb.auth.exceptions.verification.VerificationCodeExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    final private String codeKey = "code";
    final private String codeMessage = "message";

    @ExceptionHandler(CookieNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCookieNotFound(CookieNotFoundException ex) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put(codeKey, "COOKIE_NOT_FOUND");
        apiError.put(codeMessage, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VerificationCodeExpiredException.class)
    public ResponseEntity<Map<String, String>> handleVerificationCodeExpired(VerificationCodeExpiredException ex) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put(codeKey, "CODE_EXPIRED");
        apiError.put(codeMessage, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidVerificationCodeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidVerificationCode(InvalidVerificationCodeException ex) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put(codeKey, "INVALID_CODE");
        apiError.put(codeMessage, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put(codeKey, "USER_NOT_FOUND");
        apiError.put(codeMessage, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(UserExistsException ex) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put(codeKey, "USER_ALREADY_EXISTS");
        apiError.put(codeMessage, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
