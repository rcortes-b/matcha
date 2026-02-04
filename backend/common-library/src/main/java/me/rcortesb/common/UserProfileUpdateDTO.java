package me.rcortesb.common;

import java.util.List;

public record UserProfileUpdateDTO(String userId,
                                   int age,
                                   String gender,
                                   String genderPreference,
                                   double latitude,
                                   double longitude,
                                   List<String> tags) {}
