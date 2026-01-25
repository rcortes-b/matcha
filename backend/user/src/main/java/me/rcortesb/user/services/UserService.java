package me.rcortesb.user.services;

import me.rcortesb.user.domain.dto.CompleteProfileDTO;

import java.util.List;

public interface UserService {
    void completeProfile(String userId, CompleteProfileDTO completeProfileDTO);
    void updateTagSelection(String userId, List<String> tags);
}
