package me.rcortesb.interaction.service;

public interface MatchService {
	void handleLike(String userId, String targetId);
	void handleMatch(String user1, String user2, boolean doCheck);
	void deleteLikes(String user1, String user2);
}
