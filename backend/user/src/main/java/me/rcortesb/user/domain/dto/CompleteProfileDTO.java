package me.rcortesb.user.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record CompleteProfileDTO(LocalDate birthDate,
                                 @NotNull
                                 @Pattern(regexp = "(?i)MALE|FEMALE", message = "Gender must be Male or Female")
                                 String gender,
                                 @NotNull
                                 String sexualPreference,
                                 String biography,
                                 @NotNull
                                 @Size(min = 3, message = "Minimum selected tags must be 3")
                                 List<String> tags) {
}