package me.rcortesb.interaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.rcortesb.interaction.domain.dto.ReportDTO;
import me.rcortesb.interaction.service.BlockService;
import me.rcortesb.interaction.service.MatchService;

@RestController
@RequestMapping("/api/interaction")
public class InteractionController {
	final private BlockService blockService;
	final private MatchService matchService;

	public InteractionController(BlockService blockService,
								 MatchService matchService) {
		this.blockService = blockService;
		this.matchService = matchService;
	}

	@PostMapping("/like")
	public ResponseEntity<Void> handleLike(@RequestHeader("X-User-Id") String userId,
										   @RequestBody String targetId) {
		matchService.handleLike(userId, targetId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/match")
	public ResponseEntity<Void> handleMatch(@RequestHeader("X-User-Id") String userId,
										    @RequestBody String targetId) {
		matchService.handleMatch(userId, targetId, true);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/block")
	public ResponseEntity<Void> handleBlock(@RequestHeader("X-User-Id") String userId,
										    @RequestBody String targetId) {
		blockService.handleBlock(userId, targetId);
		matchService.deleteLikes(userId, targetId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/report")
	public ResponseEntity<Void> handleReport(@RequestHeader("X-User-Id") String userId,
										     @RequestBody ReportDTO report) {
		
		blockService.handleReport(userId, report);
		matchService.deleteLikes(userId, report.targetId());
		return ResponseEntity.ok().build();
	}

	@PostMapping("/unlike")
	public ResponseEntity<Void> handleLikeDeletion(@RequestHeader("X-User-Id") String userId,
										     	   @RequestBody String targetId) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/profile_view")
	public ResponseEntity<Void> handleProfileViewed(@RequestHeader("X-User-Id") String userId,
										     		@RequestBody String targetId) {
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