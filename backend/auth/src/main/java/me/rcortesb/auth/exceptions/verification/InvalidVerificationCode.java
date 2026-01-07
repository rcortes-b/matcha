package me.rcortesb.auth.exceptions.verification;

public class InvalidVerificationCode extends RuntimeException   {
    public InvalidVerificationCode(String message) {
        super(message);
    }
}
