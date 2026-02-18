package me.rcortesb.interaction.service.events;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import me.rcortesb.common.InteractionEventDTO;

@Service
public class MatchEventProducer {
	final private KafkaTemplate<String, InteractionEventDTO> kafkaTemplate;
	final String likeTopic = "like.event";
	final String unlikeTopic = "unlike.event";
	final String matchTopic = "match.event";

	public MatchEventProducer(KafkaTemplate<String, InteractionEventDTO> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMatchEvent(InteractionEventDTO dto) {
		kafkaTemplate.send(matchTopic, dto.userProducer(), dto);
		kafkaTemplate.send(matchTopic, dto.userConsumer(), dto);
	}

	public void sendLikeEvent(InteractionEventDTO dto) {
		kafkaTemplate.send(likeTopic, dto.userConsumer(), dto);
	}

	public void sendUnLikeEvent(InteractionEventDTO dto) {
		kafkaTemplate.send(unlikeTopic, dto.userConsumer(), dto);
	}
}
