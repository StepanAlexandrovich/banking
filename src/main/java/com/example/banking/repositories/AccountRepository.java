package com.example.banking.repositories;

import com.example.banking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllByUserId(Long userId);
}
