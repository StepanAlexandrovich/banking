package com.example.banking.services.impl;

import com.example.banking.models.User;
import com.example.banking.models.UserImage;
import com.example.banking.repositories.UserImageRepository;
import com.example.banking.repositories.UserRepository;
import com.example.banking.services.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserImageServiceImpl implements UserImageService {
    private final UserImageRepository userImageRepository;
    private final UserRepository userRepository;
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
    public void addImageToUserByUserId(List<MultipartFile> files, Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        List<UserImage> images = files.stream().map(this::convert).toList();
        for (UserImage image : images) {
            image.setUser(user);
            image.setActivate(true);
            userImageRepository.save(image);
        }

//        User user = userRepository.findById(userId).orElse(null);
//        List<AccountImage> images = files.stream().map(this::convert).toList();
//        for (AccountImage image : images) {
//            image.setUser(user);
//        }
//        user.setImages(images);
//        userRepository.save(user);
    }
}
