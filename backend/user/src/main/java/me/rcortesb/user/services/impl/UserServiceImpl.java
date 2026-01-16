package me.rcortesb.user.services.impl;

import me.rcortesb.user.domain.dto.CompleteProfileDTO;
import me.rcortesb.user.repository.UserRepository;
import me.rcortesb.user.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void completeProfile(CompleteProfileDTO completeProfileDTO) {

    }
}
