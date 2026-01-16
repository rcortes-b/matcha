package me.rcortesb.user.services;


import me.rcortesb.common.UserCreatedDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedConsumer {
    @KafkaListener(topics = "user.created",
                    groupId = "user-service")
    public void consume(UserCreatedDTO userCreatedDTO) {
        System.out.println("\n--------UserCreatedConsumer--------");
        System.out.println(userCreatedDTO);
        System.out.println("\n--------END--------");
    }
}
