package me.rcortesb.user.controller;

import jakarta.validation.Valid;
import me.rcortesb.user.domain.dto.CompleteProfileDTO;
import me.rcortesb.user.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/complete-profile")
    public ResponseEntity<Void> completeProfile(@RequestHeader("X-User-Id") String userId,
                                                @RequestBody @Valid CompleteProfileDTO completeProfileDTO) {
        userService.completeProfile(userId, completeProfileDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/select-tags")
    public ResponseEntity<Void> selectTags(@RequestHeader("X-User-Id") String userId,
                                           @RequestBody List<String> tags) {
        System.out.println("Selecting tags: " + tags.toString());
        userService.updateTagSelection(userId, tags);
        return ResponseEntity.ok().build();
    }	
}
