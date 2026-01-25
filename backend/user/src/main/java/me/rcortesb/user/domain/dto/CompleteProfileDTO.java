package me.rcortesb.user.domain.dto;

import java.time.LocalDate;

public record CompleteProfileDTO(LocalDate birthDate,
                                 String gender,
                                 String sexualPreference,
                                 String biography) {
}
/*   private LocalDate birthDate;
    private EGender gender;
    private SexualPreference sexualPreference;
    private String biography;*/