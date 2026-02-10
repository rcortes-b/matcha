package me.rcortesb.user.services;

import me.rcortesb.user.domain.dto.CompleteProfileDTO;
import me.rcortesb.user.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    void completeProfile(String userId, CompleteProfileDTO completeProfileDTO);
    void updateTagSelection(String userId, List<String> tags);
	Optional<User> getUserById(UUID userId);
	List<User> getUsersByIdList(List<String> userIds);
}
