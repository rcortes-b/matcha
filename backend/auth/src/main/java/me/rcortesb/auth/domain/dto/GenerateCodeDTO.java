package me.rcortesb.auth.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record GenerateCodeDTO(@Email @NotBlank String email) {}
