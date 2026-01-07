package me.rcortesb.auth.exceptions;

import me.rcortesb.auth.exceptions.verification.CookieNotFound;
import me.rcortesb.auth.exceptions.verification.InvalidVerificationCode;
import me.rcortesb.auth.exceptions.verification.VerificationCodeExpired;
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

    @ExceptionHandler(CookieNotFound.class)
    public ResponseEntity<Map<String, String>> handleCookieNotFound(CookieNotFound ex) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put(codeKey, "COOKIE_NOT_FOUND");
        apiError.put(codeMessage, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VerificationCodeExpired.class)
    public ResponseEntity<Map<String, String>> handleVerificationCodeExpired(VerificationCodeExpired ex) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put(codeKey, "CODE_EXPIRED");
        apiError.put(codeMessage, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidVerificationCode.class)
    public ResponseEntity<Map<String, String>> handleInvalidVerificationCode(InvalidVerificationCode ex) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put(codeKey, "INVALID_CODE");
        apiError.put(codeMessage, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFound ex) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put(codeKey, "USER_NOT_FOUND");
        apiError.put(codeMessage, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
