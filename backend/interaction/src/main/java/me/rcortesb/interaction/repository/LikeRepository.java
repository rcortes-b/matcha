package me.rcortesb.interaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.rcortesb.interaction.domain.entity.LikeEntity;
import me.rcortesb.interaction.domain.entity.composite.UserTargetId;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, UserTargetId> {}
