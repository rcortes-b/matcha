package me.rcortesb.interaction.service;

import me.rcortesb.interaction.domain.dto.ReportDTO;

public interface InteractionService {
	void handleLike(String userId, String targetId);
	void handleBlock(String userId, String targetId);
	void handleReport(String userId, ReportDTO report);
	void handleMatch(String user1, String user2, boolean doCheck);
}
