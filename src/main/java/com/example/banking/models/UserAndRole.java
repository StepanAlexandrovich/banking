package com.example.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_and_roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAndRole {
    @EmbeddedId
    private UserAndRoleKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("userRoleId")
    @JoinColumn(name = "user_role_id")
    private UserRole userRole;


}
