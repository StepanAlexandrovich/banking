package com.example.banking.services;

import com.example.banking.models.Account;
import com.example.banking.models.User;

import java.util.List;

public interface AccountService {
    Account getById(Long id);
    List<Account> getAll();
    Account addAccount(Account account);
    Account createAccount(Long userId);
    Account createAccount(User user);
    Account createAccount(User user,String accountName);
    Account activateAccount(Account account);
    Account deactivateAccount(Account account);
    Account changeStateAccount(Account account);

    List<Account> getAccountsByUserId(Long userId);

    Account addMoney(Double money,Account account);
    Account setMoney(Double money,Account account);
    List<Account> transfer(Double money,Account AccountFrom,Account accountTo);
}
