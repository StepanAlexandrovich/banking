package com.example.banking.services;


import com.example.banking.dto.UserCreateDto;
import com.example.banking.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    void createUser(UserCreateDto userCreateDto);

    List<User> getAll();
    User getById(Long id);

    void addImageToUserByUserId(List<MultipartFile> files,Long userId);
    List<User> getAllByUserRoleId(Long userRoleId);
}
