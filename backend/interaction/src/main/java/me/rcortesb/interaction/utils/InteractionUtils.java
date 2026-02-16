package me.rcortesb.interaction.utils;

import java.util.UUID;

import me.rcortesb.interaction.domain.entity.composite.UserTargetId;

public class InteractionUtils {
	private static InteractionUtils interactionUtils = null;

	private InteractionUtils() {}

	public static InteractionUtils getInteractionUtils() {
		if (interactionUtils == null)
			interactionUtils = new InteractionUtils();
		return interactionUtils;
	}

	public UserTargetId createCompositeKey(String userId, String targetId) {
		UUID user1 = UUID.fromString(userId);
		UUID user2 = UUID.fromString(targetId);

		return new UserTargetId(user1, user2);
	}
}
