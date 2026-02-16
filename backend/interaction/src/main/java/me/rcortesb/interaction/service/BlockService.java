package me.rcortesb.interaction.service;

import me.rcortesb.interaction.domain.dto.ReportDTO;
import me.rcortesb.interaction.domain.entity.composite.UserTargetId;

public interface BlockService {
	void handleBlock(String userId, String targetId);
	void handleReport(String userId, ReportDTO report);
	boolean userIsBlocked(UserTargetId id);
}
