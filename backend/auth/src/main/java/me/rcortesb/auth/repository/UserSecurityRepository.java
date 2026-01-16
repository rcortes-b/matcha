package me.rcortesb.auth.repository;

import me.rcortesb.auth.domain.entity.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurity, UUID> {
    UserSecurity findByEmail(String email);
}
