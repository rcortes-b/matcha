package me.rcortesb.interaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.rcortesb.interaction.domain.entity.BlockEntity;
import me.rcortesb.interaction.domain.entity.composite.UserTargetId;

@Repository
public interface BlockRepository extends JpaRepository<BlockEntity, UserTargetId> {}
