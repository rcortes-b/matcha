package me.rcortesb.user.domain.dto;

public record CompleteProfileDTO(String birthDate,
                                 String gender,
                                 String sexualPreference,
                                 String biography) {
}
/*   private LocalDate birthDate;
    private EGender gender;
    private SexualPreference sexualPreference;
    private String biography;*/