package me.rcortesb.auth.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EmailVerificationDTO(@Email @NotBlank String email,
                                   @Pattern(regexp = "^[0-9]{6}$", message = "Must be exactly 6 digits")
                                   String code) {
}
