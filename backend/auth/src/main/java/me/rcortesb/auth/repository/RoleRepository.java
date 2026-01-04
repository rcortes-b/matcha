package me.rcortesb.auth.repository;

import me.rcortesb.auth.domain.common.ERole;
import me.rcortesb.auth.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
    Role findByRole(ERole role);
}
