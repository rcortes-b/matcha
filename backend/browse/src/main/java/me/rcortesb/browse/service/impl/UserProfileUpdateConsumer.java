package me.rcortesb.browse.service.impl;

import me.rcortesb.browse.domain.entity.UserDocument;
import me.rcortesb.common.UserProfileUpdateDTO;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserProfileUpdateConsumer {
    final private ElasticsearchOperations elasticsearchOperations;

    public UserProfileUpdateConsumer(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @KafkaListener(topics = "profile.update", groupId = "browse-service")
    public void consume(UserProfileUpdateDTO userProfileUpdateDTO) {
        UserDocument userDocument = mapToUserDocument(userProfileUpdateDTO);
        elasticsearchOperations.save(userDocument);
    }

    private UserDocument mapToUserDocument(UserProfileUpdateDTO userProfileUpdateDTO) {
        UserDocument userDocument = new UserDocument();
        userDocument.setId(userProfileUpdateDTO.userId());
        userDocument.setAge(userProfileUpdateDTO.age());
        userDocument.setGender(userProfileUpdateDTO.gender());
        userDocument.setSexualPreference(userProfileUpdateDTO.genderPreference());
        userDocument.setLocation(new GeoPoint(userProfileUpdateDTO.latitude(),  userProfileUpdateDTO.longitude()));
        userDocument.setTags(userProfileUpdateDTO.tags());
        return userDocument;
    }
}