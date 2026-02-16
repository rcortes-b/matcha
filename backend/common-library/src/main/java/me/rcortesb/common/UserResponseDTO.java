package me.rcortesb.common;

import java.util.List;

public record UserResponseDTO(String userId,
							  String firstName,
							  int age,
							  String gender,
							  String sexualPreference,
							  String biography,
							  String address,
							  List<String> tags) {}
