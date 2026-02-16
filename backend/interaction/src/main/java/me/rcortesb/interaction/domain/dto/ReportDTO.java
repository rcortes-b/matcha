package me.rcortesb.interaction.domain.dto;

import org.hibernate.validator.constraints.UUID;

import jakarta.validation.constraints.NotEmpty;

public record ReportDTO(@UUID String targetId,
						@NotEmpty String reason) {}