package me.rcortesb.user.services.impl;

import me.rcortesb.common.UserProfileUpdateDTO;
import me.rcortesb.user.domain.entity.Tag;
import me.rcortesb.user.domain.entity.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileUpdateProducer {
	final private KafkaTemplate<String, UserProfileUpdateDTO> kafkaTemplate;

    public UserProfileUpdateProducer(KafkaTemplate<String, UserProfileUpdateDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserProfileUpdateEvent(User user) {
        UserProfileUpdateDTO userProfileUpdateDTO = mapFromEntity(user);
        kafkaTemplate.send("profile.update", userProfileUpdateDTO.userId(), userProfileUpdateDTO);
    }

    private UserProfileUpdateDTO mapFromEntity(User user) {
        System.out.println("\n\nAGE: " + Period.between(user.getBirthDate(), LocalDate.now()) + "\n\n");

        List<String> tags = new ArrayList<>();
        for (Tag tag : user.getTags())
            tags.add(tag.getTagName());

        UserProfileUpdateDTO dto = new UserProfileUpdateDTO(user.getId().toString(),
                user.getAge(),
                user.getGender().toString(),
                user.getSexualPreference().getPreference(),
                user.getLatitude(),
                user.getLongitude(),
                tags);
        System.out.println("DTO: " + dto.toString() + "\n\n");
        return dto;
    }
}
