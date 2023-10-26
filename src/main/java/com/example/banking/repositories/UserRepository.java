package com.example.banking.repositories;

import com.example.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByLogin(String login);

//    @Query(value = "SELECT* FROM public.users WHERE login = :login",nativeQuery = true)
//    List<User> findByLogin(String login);
}
