package me.rcortesb.user.services.impl;


import me.rcortesb.common.UserCreatedDTO;
import me.rcortesb.user.domain.entity.User;
import me.rcortesb.user.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCreatedConsumer {
    final private UserRepository userRepository;

    public UserCreatedConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "user.created",
                    groupId = "user-service")
    public void consume(UserCreatedDTO userCreatedDTO) {
        System.out.println("\n--------UserCreatedConsumer--------");
        System.out.println(userCreatedDTO);
        User newUser = new User(UUID.fromString(userCreatedDTO.userId()),
                                userCreatedDTO.email(),
                                userCreatedDTO.firstName(),
                                userCreatedDTO.lastName());
        userRepository.save(newUser);
        System.out.println("\n--------END--------");
    }
}
