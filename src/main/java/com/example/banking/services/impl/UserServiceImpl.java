package com.example.banking.services.impl;

import com.example.banking.dto.UserCreateDto;
import com.example.banking.models.*;
import com.example.banking.repositories.AccountImageRepository;
import com.example.banking.repositories.UserAndRoleRepository;
import com.example.banking.repositories.UserRepository;
import com.example.banking.repositories.UserRoleRepository;
import com.example.banking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserAndRoleRepository userAndRoleRepository;
    //private final UserCreateDtoToUserTransformer<User> userCreateDtoToUserTransformer;
    private final PasswordEncoder passwordEncoder;

    private final AccountImageRepository accountImageRepository;

    @Override
    public void createUser(UserCreateDto userCreateDto) {
//        User user = userCreateDtoToUserTransformer.transform(userCreateDto);
//        user.setUserRole(userRoleRepository.findByRole("ROLE_USER"));

//        User user = new User(userCreateDto.getLogin(),userCreateDto.getPassword(),"name");
        User user = new User(userCreateDto.getLogin(), passwordEncoder.encode(userCreateDto.getPassword()), "name");
        userRepository.save(user);

//        user = userRepository.findByLogin(userCreateDto.getLogin()).get(0);
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
    public void addImageToUserByUserId(List<MultipartFile> files, Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        List<UserImage> images = files.stream().map(this::convert).toList();
        for (UserImage image : images) {
            image.setUser(user);
            accountImageRepository.save(image);
        }

//        User user = userRepository.findById(userId).orElse(null);
//        List<AccountImage> images = files.stream().map(this::convert).toList();
//        for (AccountImage image : images) {
//            image.setUser(user);
//        }
//        user.setImages(images);
//        userRepository.save(user);
    }
    private UserImage convert(MultipartFile file){
        UserImage image = new UserImage();

        try {
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
            image.setOriginalFileName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
    @Override
    public List<User> getAllByUserRoleId(Long userRoleId) {
        List<UserAndRole> userAndRoles = userAndRoleRepository.findByUserRoleId(userRoleId);
        return userAndRoles.stream().map(UserAndRole::getUser).toList();
    }

}
