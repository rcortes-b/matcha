package me.rcortesb.browse.controller;

import me.rcortesb.browse.domain.entity.dto.SearchFilter;
import me.rcortesb.browse.service.BrowseService;
import me.rcortesb.common.UserResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@RequestMapping("/api/browse")
public class BrowseController {
    final private BrowseService browseService;

    public BrowseController(BrowseService browseService) {
        this.browseService = browseService;
    }

	@GetMapping
    public ResponseEntity<UserResponseDTO> searchByDistance(@RequestHeader("X-User-Id") String userId,
															@ModelAttribute SearchFilter filters) {
		browseService.searchUsersByDistance(userId, filters);
		return ResponseEntity.ok(null);
	}
}
