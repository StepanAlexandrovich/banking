package com.example.banking.services.impl;

import com.example.banking.dto.UserCreateDto;
import com.example.banking.models.*;
import com.example.banking.repositories.UserAndRoleRepository;
import com.example.banking.repositories.UserRepository;
import com.example.banking.repositories.UserRoleRepository;
import com.example.banking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserAndRoleRepository userAndRoleRepository;
    //private final UserCreateDtoToUserTransformer<User> userCreateDtoToUserTransformer;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserCreateDto userCreateDto) {
        User user = new User(userCreateDto.getLogin(), passwordEncoder.encode(userCreateDto.getPassword()), "name");
        userRepository.save(user);

        user = userRepository.findByLogin(userCreateDto.getLogin());
        UserRole userRole = userRoleRepository.findByRole("ROLE_USER");

        UserAndRoleKey userAndRoleKey = new UserAndRoleKey(user.getId(),userRole.getId());
        UserAndRole userAndRole = new UserAndRole(userAndRoleKey,user,userRole);

        userAndRoleRepository.save(userAndRole);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllByUserRoleId(Long userRoleId) {
        List<UserAndRole> userAndRoles = userAndRoleRepository.findByUserRoleId(userRoleId);
        return userAndRoles.stream().map(UserAndRole::getUser).toList();
    }

}
