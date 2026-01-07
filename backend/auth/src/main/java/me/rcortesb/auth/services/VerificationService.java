package me.rcortesb.auth.services;

public interface VerificationService {
    String generateVerificationCode();
    void sendEmail(String email, String verificationCode);
    void verifyCode(String email, String code);
    void verifyUser(String email);
}
