package me.rcortesb.auth.domain.entity;

import jakarta.persistence.*;
import me.rcortesb.auth.domain.common.ERole;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ERole role;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role.toString() + '\'' +
                '}';
    }
}
