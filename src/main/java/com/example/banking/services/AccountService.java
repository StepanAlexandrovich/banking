package com.example.banking.services;

import com.example.banking.models.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account addAccount(Account account);
    Account createAccount(Long userId);

    List<Account> getAccountsByUserId(Long userId);
}
