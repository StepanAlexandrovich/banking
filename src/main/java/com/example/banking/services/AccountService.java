package com.example.banking.services;

import com.example.banking.models.Account;

import java.util.List;

public interface AccountService {
    Account getById(Long id);
    List<Account> getAll();
    Account addAccount(Account account);
    Account createAccount(Long userId);

    List<Account> getAccountsByUserId(Long userId);
}
