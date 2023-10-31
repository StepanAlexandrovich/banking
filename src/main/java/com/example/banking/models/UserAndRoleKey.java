package com.example.banking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAndRoleKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_role_id")
    private Long userRoleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAndRoleKey that = (UserAndRoleKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userRoleId, that.userRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userRoleId);
    }

}
