package com.example.banking.services.impl;

import com.example.banking.models.Account;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.repositories.UserRepository;
import com.example.banking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public Account getById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account createAccount(Long userId) {
        Account account = new Account();
        account.setAmount(0.0);
        account.setUser(userRepository.findById(userId).orElse(null));
        accountRepository.save(account);
        return null;
    }

    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        return accountRepository.findAllByUserId(userId);
    }

    @Override
    public Account addMoney(Double money, Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        account.setAmount(account.getAmount() + money);
        return accountRepository.save(account);
    }
    @Override
    public List<Account> transfer(Double money, Long accountIdFrom, Long accountIdTo) {
        List<Account> accounts = new ArrayList<>();
        Account accountFrom = accountRepository.findById(accountIdFrom).orElse(null);
        Account accountTo = accountRepository.findById(accountIdTo).orElse(null);
        accountFrom.setAmount(accountFrom.getAmount() - money);
        accountTo.setAmount(accountTo.getAmount() + money);
        accounts.add(accountRepository.save(accountFrom));
        accounts.add(accountRepository.save(accountTo));
        return accounts;
    }

}
