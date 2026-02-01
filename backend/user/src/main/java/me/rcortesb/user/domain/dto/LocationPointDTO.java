package me.rcortesb.user.domain.dto;

import jakarta.validation.constraints.NotNull;

public record LocationPointDTO(@NotNull double latitude,
                               @NotNull double longitude) {}
