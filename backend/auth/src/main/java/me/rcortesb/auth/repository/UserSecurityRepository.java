package me.rcortesb.auth.repository;

import me.rcortesb.auth.domain.entity.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    UserSecurity findByEmail(String email);
}
