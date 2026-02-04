package me.rcortesb.browse.controller;

import me.rcortesb.browse.service.BrowseService;
import me.rcortesb.common.UserResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/browse")
public class BrowseController {
    final private BrowseService browseService;

    public BrowseController(BrowseService browseService) {
        this.browseService = browseService;
    }

	@GetMapping
    public ResponseEntity<UserResponseDTO> searchByDistance(@RequestHeader("X-User-Id") String userId
															) {
		return ResponseEntity.ok(null);
	}
}
