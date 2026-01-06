package me.rcortesb.auth.services.impl;

import me.rcortesb.auth.domain.entity.UserSecurity;
import me.rcortesb.auth.repository.UserSecurityRepository;
import me.rcortesb.auth.services.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
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
        redisTemplate.opsForValue().set(key, verificationCode, Duration.ofMinutes(1));
    }

    @Override
    public void verifyCode(String email, String code) {
        final String key = "verification-code:" + email;
        String storedCode = redisTemplate.opsForValue().get(key);
        if (storedCode == null) {
            //throw CodeExpired
            throw new RuntimeException("Verification code not found");
        } else if (!storedCode.equals(code)) {
            //throw InvalidCode
            throw new RuntimeException("Verification code does not match");
        }
        UserSecurity user =  userSecurityRepository.findByEmail(email);
        if (user == null)
            throw new RuntimeException("User not found");
        user.setVerified(true);
        if (!email.equals(user.getEmail())) {
            user.setEmail(email); // In case it's a change-email
        }
        userSecurityRepository.save(user);
        redisTemplate.delete(key);
    }
}
