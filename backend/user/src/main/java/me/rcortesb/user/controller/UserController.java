package me.rcortesb.user.controller;

import jakarta.validation.Valid;
import me.rcortesb.user.domain.dto.CompleteProfileDTO;
import me.rcortesb.user.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/complete-profile")
    public ResponseEntity<Void> completeProfile(@RequestBody @Valid CompleteProfileDTO completeProfileDTO) {
        userService.completeProfile(completeProfileDTO);
        return ResponseEntity.ok().build();
    }
}
