package me.rcortesb.interaction.service.events;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeEventProducer {
	final private KafkaTemplate<String, Object> kafkaTemplate;

	public LikeEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	//kafkaTemplate.send("user.liked", userProfileUpdateDTO.userId(), userProfileUpdateDTO);
}
