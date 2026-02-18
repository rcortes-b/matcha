package me.rcortesb.interaction.service.events;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import me.rcortesb.common.InteractionEventDTO;

@Service
public class ProfileViewEventProducer {
	final private KafkaTemplate<String, InteractionEventDTO> kafkaTemplate;
	final String profileViewTopic = "profileview.event";

	public ProfileViewEventProducer(KafkaTemplate<String, InteractionEventDTO> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendProfileViewEvent(InteractionEventDTO dto) {
		kafkaTemplate.send(profileViewTopic, dto.userConsumer(), dto);
	}
}
