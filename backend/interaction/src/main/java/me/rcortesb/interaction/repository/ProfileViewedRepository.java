package me.rcortesb.interaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.rcortesb.interaction.domain.entity.ProfileViewedEntity;
import me.rcortesb.interaction.domain.entity.composite.UserTargetId;

@Repository
public interface ProfileViewedRepository extends JpaRepository<ProfileViewedEntity, UserTargetId> {}
