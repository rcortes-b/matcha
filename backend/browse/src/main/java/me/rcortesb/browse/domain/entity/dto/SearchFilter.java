package me.rcortesb.browse.domain.entity.dto;

import java.util.List;

public record SearchFilter(List<String> tags,
							int minAge,
							int maxAge,
							int distance) {
	
}
