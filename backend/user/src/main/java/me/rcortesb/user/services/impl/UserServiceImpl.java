package me.rcortesb.user.services.impl;

import jakarta.transaction.Transactional;
import me.rcortesb.user.domain.common.EGender;
import me.rcortesb.user.domain.dto.CompleteProfileDTO;
import me.rcortesb.user.domain.entity.SexualPreference;
import me.rcortesb.user.domain.entity.Tag;
import me.rcortesb.user.domain.entity.User;
import me.rcortesb.user.repository.SexualPreferenceRepository;
import me.rcortesb.user.repository.TagRepository;
import me.rcortesb.user.repository.UserRepository;
import me.rcortesb.user.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;
    final private SexualPreferenceRepository sexualPreferenceRepository;
    final private TagRepository tagRepository;

    public UserServiceImpl(UserRepository userRepository, SexualPreferenceRepository sexualPreferenceRepository,
                           TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.sexualPreferenceRepository = sexualPreferenceRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public void completeProfile(String userId, CompleteProfileDTO completeProfileDTO) {
        UUID uuid = UUID.fromString(userId);
        User user = userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("user not found"));
        SexualPreference sexualPreference = sexualPreferenceRepository
                                            .findByPreference(completeProfileDTO.sexualPreference().toUpperCase());
        if (sexualPreference == null) {
            throw new RuntimeException("Sexual preference is invalid");
        }
        user.setGender(EGender.valueOf(completeProfileDTO.gender().toUpperCase()));
        user.setSexualPreference(sexualPreference);
        user.setBirthDate(completeProfileDTO.birthDate());
        user.setBiography(completeProfileDTO.biography());
        getListOfNewTags(completeProfileDTO.tags(), user.getTags());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateTagSelection(String userId, List<String> tags) {
        UUID uuid = UUID.fromString(userId);
        User user = userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("user not found"));
        Set<Tag> userTags = user.getTags();

        Iterator<Tag> it = userTags.iterator();
        while (it.hasNext()) {
            String tagName = it.next().getTagName();
            if (!tags.contains(tagName)) {
                it.remove();
            }
        }
        getListOfNewTags(tags, userTags);
    }

	@Override
	@Transactional
	public Optional<User> getUserById(UUID userId) {
		return userRepository.findById(userId);
	}

	@Override
	@Transactional
	public List<User> getUsersByIdList(List<String> userIds) {
		return userRepository.findAllById(userIds.stream()
												 .map(UUID::fromString)
												 .toList());
	}

    public void getListOfNewTags(List<String> tags, Set<Tag> userTags) {
        for (String tagName : tags) {
            Tag newTag = tagRepository.findByTagName(tagName);
            if (newTag == null) {
                throw new RuntimeException("tag not found");
            }
            if (!userTags.contains(newTag)) {
                userTags.add(newTag);
            }
        }
    }
}
