package com.example.banking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{

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
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<UserAndRole> userAndRoles;
    @OneToMany(mappedBy = "user")
    private List<UserImage> images = new ArrayList<>();

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }
    public int getAccountsSize(){ return accounts.size(); }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserRole> userRoles = new ArrayList<>();
        for (UserAndRole userAndRole: userAndRoles) {
            UserRole userRole = userAndRole.getUserRole();
            userRole.setUserAndRoles(null);
            userRoles.add(userRole);
        }
        return userRoles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        return true;   // переделать
    }
}
