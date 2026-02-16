package me.rcortesb.interaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interaction")
public class InteractionController {
	public InteractionController() {}

	@PostMapping("/likes")
	public ResponseEntity<Void> handleLike(@RequestHeader("X-User-Id") String userId,
										   @RequestBody String targetId) {
		//
		return ResponseEntity.ok().build();
	}
}

/*
	Like
	Unlike
	Match
	Block
	Report
	Viewed

	Like -> Store in Db + send event -> Check for match -> Send match event
*/