package me.rcortesb.browse.service.impl;

import me.rcortesb.browse.domain.entity.UserDocument;
import me.rcortesb.common.UserProfileUpdateDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class UserProfileUpdateConsumer {
    final private ElasticsearchOperations elasticsearchOperations;

    public UserProfileUpdateConsumer(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @KafkaListener(topics = "profile.update", groupId = "browse-service")
    public void consume(UserProfileUpdateDTO userProfileUpdateDTO) {
        UserDocument userDocument = mapToUserDocument(userProfileUpdateDTO);
		System.out.println("UserDocument: " + userDocument.toString());
        elasticsearchOperations.save(userDocument);
		System.out.println("Document saved!");
    }

    private UserDocument mapToUserDocument(UserProfileUpdateDTO userProfileUpdateDTO) {
        UserDocument userDocument = new UserDocument();
        userDocument.setId(userProfileUpdateDTO.userId());
        userDocument.setAge(userProfileUpdateDTO.age());
        userDocument.setGender(userProfileUpdateDTO.gender());
        userDocument.setInterestedIn(getInterestedIn(userDocument.getGender(), userProfileUpdateDTO.genderPreference()));
        userDocument.setLocation(new GeoPoint(userProfileUpdateDTO.latitude(),  userProfileUpdateDTO.longitude()));
        userDocument.setTags(userProfileUpdateDTO.tags());
        return userDocument;
    }

	@PostConstruct
	private void createIndexIfNotExists() {
		IndexOperations indexOps = elasticsearchOperations.indexOps(UserDocument.class);

		if (!indexOps.exists()) {
			if (indexOps.create()) {
				indexOps.putMapping(indexOps.createMapping(UserDocument.class));
				System.out.println("Index created!");
			} else {
				System.out.println("Index already exists.");
			}
    	} else {
			System.out.println("FAIL");
		}
	}

	private List<String> getInterestedIn(String gender, String sexualPreference) {
		List<String> interestedIn = new ArrayList<>();

		if ("MALE".equals(gender)) {
			if ("HETEROSEXUAL".equals(sexualPreference))
				interestedIn.add("FEMALE");
			else if ("HOMOSEXUAL".equals(sexualPreference))
				interestedIn.add("MALE");
		} else {
			if ("HETEROSEXUAL".equals(sexualPreference))
				interestedIn.add("MALE");
			else if ("HOMOSEXUAL".equals(sexualPreference))
				interestedIn.add("FEMALE");
		}

		if (interestedIn.isEmpty()) {
			interestedIn.add("FEMALE");
			interestedIn.add("MALE");
		}
		return interestedIn;
	}
}