package me.rcortesb.auth.controller;

import jakarta.validation.Valid;
import me.rcortesb.auth.domain.dto.EmailVerificationDTO;
import me.rcortesb.auth.domain.dto.GenerateCodeDTO;
import me.rcortesb.auth.services.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/verification")
public class VerificationController {
    final private VerificationService verificationService;
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping("/generate-code")
    public ResponseEntity<Void> generateCode(@RequestBody @Valid GenerateCodeDTO generateCodeDTO) {
        final String code = verificationService.generateVerificationCode();
        verificationService.sendEmail(generateCodeDTO.email(), code);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-email")
    public ResponseEntity<Void> verifyEmail(@RequestBody @Valid EmailVerificationDTO emailVerificationDTO) {
        verificationService.verifyCode(emailVerificationDTO.email(), emailVerificationDTO.code());
        verificationService.verifyUser(emailVerificationDTO.email());
        return ResponseEntity.ok().build();
    }
}
