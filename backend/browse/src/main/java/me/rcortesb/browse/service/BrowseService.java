package me.rcortesb.browse.service;

import java.util.List;

import me.rcortesb.browse.domain.entity.dto.SearchFilter;
import me.rcortesb.common.UserResponseDTO;

public interface BrowseService {
	public List<UserResponseDTO> searchUsersByDistance(String userId, SearchFilter filter);
}
