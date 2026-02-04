package me.rcortesb.common;

import java.util.List;

public record UserResponseDTO(String firstName,
								int age,
								String gender,
								String biography,
								int distance,
								List<String> tags) {}
