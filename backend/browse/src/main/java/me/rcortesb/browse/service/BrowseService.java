package me.rcortesb.browse.service;

import java.util.List;

import me.rcortesb.common.UserResponseDTO;

public interface BrowseService {
	public List<UserResponseDTO> searchUsersByDistance();
}
