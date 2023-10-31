package com.example.banking.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserImageService {
    void addImageToUserByUserId(List<MultipartFile> files, Long userId);
}
