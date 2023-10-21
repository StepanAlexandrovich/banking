package com.example.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    List<UserAndRole> userAndRoles;

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }
}
