package me.rcortesb.interaction.domain.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.rcortesb.interaction.domain.entity.composite.UserTargetId;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "blocks")
public class BlockEntity {
	@EmbeddedId
	private UserTargetId id;

	@Column(name = "created_at")
	private Instant createdAt;
}
