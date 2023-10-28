package com.example.banking.repositories;


import com.example.banking.models.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountImageRepository extends JpaRepository<UserImage,Long> {
}
