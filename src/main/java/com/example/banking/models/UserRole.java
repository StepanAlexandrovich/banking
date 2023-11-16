package com.example.banking.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
public class UserRole implements GrantedAuthority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "userRole",fetch = FetchType.EAGER)
    List<UserAndRole> userAndRoles;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
