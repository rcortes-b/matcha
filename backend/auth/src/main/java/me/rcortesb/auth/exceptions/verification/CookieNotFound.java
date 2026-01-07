package me.rcortesb.auth.exceptions.verification;

public class CookieNotFound extends RuntimeException {
    public CookieNotFound(String message) {
        super(message);
    }
}
