package me.rcortesb.auth.services.impl;

import me.rcortesb.common.UserCreatedDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedProducer {
    final private KafkaTemplate<String, UserCreatedDTO> kafkaTemplate;

    public UserCreatedProducer(KafkaTemplate<String, UserCreatedDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreatedEvent(UserCreatedDTO userCreatedDTO) {
        kafkaTemplate.send("user.created", userCreatedDTO.userId(), userCreatedDTO);
    }
}
