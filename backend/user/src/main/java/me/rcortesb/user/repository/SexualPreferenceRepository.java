package me.rcortesb.user.repository;

import me.rcortesb.user.domain.entity.SexualPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexualPreferenceRepository extends JpaRepository<SexualPreference, Short> {
    SexualPreference findByPreference(String preference);
}
