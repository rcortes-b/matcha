package me.rcortesb.auth.services.impl;

import me.rcortesb.auth.domain.entity.UserSecurity;
import me.rcortesb.auth.exceptions.UserNotFoundException;
import me.rcortesb.auth.exceptions.verification.InvalidVerificationCodeException;
import me.rcortesb.auth.exceptions.verification.VerificationCodeExpiredException;
import me.rcortesb.auth.repository.UserSecurityRepository;
import me.rcortesb.auth.services.VerificationService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;

@Service
public class VerificationServiceImpl implements VerificationService {
    private final JavaMailSender mailSender;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserSecurityRepository userSecurityRepository;

    public VerificationServiceImpl(JavaMailSender javaMailSender,
                                   RedisTemplate<String, String> redisTemplate,
                                   UserSecurityRepository userSecurityRepository) {
        this.mailSender = javaMailSender;
        this.redisTemplate = redisTemplate;
        this.userSecurityRepository = userSecurityRepository;
    }

    @Override
    public String generateVerificationCode() {
        final String CHARACTERS = "0123456789";
        final int CODE_LENGTH = 6;
        final SecureRandom random = new SecureRandom();

        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }

    @Override
    public void sendEmail(String email, String verificationCode) {
        final String fromEmail = "raulcortes.dev@gmail.com";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject("Email Verification Code");
        message.setText("Your verification code is: " + verificationCode);
        mailSender.send(message);
        final String key = "verification-code:" + email;
        redisTemplate.opsForValue().set(key, verificationCode, Duration.ofMinutes(15));
    }

    @Override
    public void verifyCode(String email, String code) {
        final String key = "verification-code:" + email;
        final String storedCode = redisTemplate.opsForValue().get(key);
        if (storedCode == null) {
            throw new VerificationCodeExpiredException("Verification code not found. Probably expired.");
        } else if (!storedCode.equals(code)) {
            throw new InvalidVerificationCodeException("Verification code don't match");
        }
        redisTemplate.delete(key);
    }

    @Override
    public void verifyUser(String email) {
        UserSecurity user =  userSecurityRepository.findByEmail(email);
        if (user == null)
            throw new UserNotFoundException("User with email " + email + " does not exist");
        user.setVerified(true);
        userSecurityRepository.save(user);
    }
}
