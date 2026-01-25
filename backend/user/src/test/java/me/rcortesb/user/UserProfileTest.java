package me.rcortesb.user;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import me.rcortesb.user.domain.common.EGender;
import me.rcortesb.user.domain.dto.CompleteProfileDTO;
import me.rcortesb.user.domain.entity.SexualPreference;
import me.rcortesb.user.domain.entity.Tag;
import me.rcortesb.user.domain.entity.User;
import me.rcortesb.user.repository.SexualPreferenceRepository;
import me.rcortesb.user.repository.TagRepository;
import me.rcortesb.user.repository.UserRepository;
import me.rcortesb.user.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserProfileTest {

    @Mock
    UserRepository userRepository;

    @Mock
    TagRepository tagRepository;

    @Mock
    SexualPreferenceRepository sexualPreferenceRepository;

    @Mock
    EGender eGender;

    @InjectMocks
    UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(UUID.randomUUID(),
                        "csraulcb@gmail.com",
                        "Raul",
                        "Cortes");

    }

    @DisplayName("JUnit Complete Profile Test")
    @Test
    void completeProfileTest() {
        UUID uuid = user.getId();
        final LocalDate now = LocalDate.now();
        final String birthDate = now.toString();
        final String gender = "MALE";
        final String sexualPreference = "HETEROSEXUAL";
        final String biography = "I spend most part of the day programming and I'm fine with that!";
        CompleteProfileDTO completeProfileDTO = new CompleteProfileDTO(now, gender, sexualPreference, biography);

        SexualPreference sexualPreferenceObj = new SexualPreference(Short.valueOf("1"), sexualPreference);

        when(sexualPreferenceRepository.findByPreference(sexualPreference)).thenReturn(sexualPreferenceObj);
        when(userRepository.findById(uuid)).thenReturn(Optional.of(user));
        given(userRepository.save(any(User.class))).willReturn(user);

        userService.completeProfile(uuid.toString(), completeProfileDTO);

        assertEquals("MALE", user.getGender().toString());
        assertEquals(now, user.getBirthDate());
        assertEquals(biography, user.getBiography());
        assertEquals(sexualPreferenceObj, user.getSexualPreference());

        verify(userRepository).findById(uuid);
        verify(sexualPreferenceRepository).findByPreference(sexualPreference);
        verify(userRepository).save(any(User.class));
    }



    @DisplayName("JUnit Tag Selection Test")
    @Test
    void tagSelectionTest() {
        completeProfileTest();
        System.out.println(user.toString());
        List<String> tags = new ArrayList<>();
        tags.add("fitness");
        tags.add("technology");

        Tag fitnessTag = new Tag(Short.valueOf("1"), "fitness");
        Tag techTag = new Tag(Short.valueOf("2"), "technology");

        when(tagRepository.findByTagName("fitness")).thenReturn(fitnessTag);
        when(tagRepository.findByTagName("technology")).thenReturn(techTag);

        System.out.println("\n Before: " + user.getTags());
        userService.updateTagSelection(user.getId().toString(), tags);
        System.out.println("\n After: " + user.getTags());
        System.out.println(user.getTags());
        assertEquals(false, user.getTags().contains("default"));
        assertEquals(true, user.getTags().contains(fitnessTag));
        assertEquals(true, user.getTags().contains(techTag));


    }

}
