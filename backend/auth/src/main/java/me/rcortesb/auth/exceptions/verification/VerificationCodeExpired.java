package me.rcortesb.auth.exceptions.verification;

public class VerificationCodeExpired extends RuntimeException {
    public VerificationCodeExpired(String message) {
        super(message);
    }
}
