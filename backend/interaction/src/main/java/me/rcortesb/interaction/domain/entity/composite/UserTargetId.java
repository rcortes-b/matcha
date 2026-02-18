package me.rcortesb.interaction.domain.entity.composite;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class UserTargetId implements Serializable {
	private UUID userId;
	private UUID targetId;

	public UserTargetId() {}

	public UserTargetId(String userId, String targetId) {
		if (userId == null || targetId == null)
			return ;
		this.userId = UUID.fromString(userId);
		this.targetId = UUID.fromString(targetId);
	}

	public UserTargetId(UUID userId, UUID targetId) {
		this.userId = userId;
		this.targetId = targetId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, targetId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTargetId other = (UserTargetId) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (targetId == null) {
			if (other.targetId != null)
				return false;
		} else if (!targetId.equals(other.targetId))
			return false;
		return true;
	}

	public void reverseIds() {
		UUID tmp = this.targetId;
		this.targetId = this.userId;
		this.userId = tmp;
	}
}
