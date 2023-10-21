package com.example.banking.services.impl;

import com.example.banking.dto.UserCreateDto;
import com.example.banking.models.User;
import com.example.banking.models.UserAndRole;
import com.example.banking.models.UserAndRoleKey;
import com.example.banking.models.UserRole;
import com.example.banking.repositories.UserAndRoleRepository;
import com.example.banking.repositories.UserRepository;
import com.example.banking.repositories.UserRoleRepository;
import com.example.banking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserAndRoleRepository userAndRoleRepository;
    //private final UserCreateDtoToUserTransformer<User> userCreateDtoToUserTransformer;

    @Override
    public void createUser(UserCreateDto userCreateDto) {
//        User user = userCreateDtoToUserTransformer.transform(userCreateDto);
//        user.setUserRole(userRoleRepository.findByRole("ROLE_USER"));

        User user = new User(userCreateDto.getLogin(),userCreateDto.getPassword(),"name");
        userRepository.save(user);

        user = userRepository.findByLogin(userCreateDto.getLogin()).get(0);
        UserRole userRole = userRoleRepository.findById(1L).orElse(null);

        UserAndRoleKey userAndRoleKey = new UserAndRoleKey(user.getId(),userRole.getId());
        UserAndRole userAndRole = new UserAndRole(userAndRoleKey,user,userRole);

        userAndRoleRepository.save(userAndRole);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllByUserRoleId(Long userRoleId) {
        userAndRoleRepository.findById(new UserAndRoleKey(1L,userRoleId));

        List<UserAndRole> userAndRoles = userAndRoleRepository.findByUserRoleId(userRoleId);

        //List<User> users = userAndRoles.stream().map(this::convert).toList();

        return userAndRoles.stream().map(userAndRole -> userAndRole.getUser()).toList();
    }

    public User method(UserAndRole userAndRole){
        return userAndRole.getUser();
    }



}
