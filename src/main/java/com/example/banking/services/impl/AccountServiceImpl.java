package com.example.banking.services.impl;

import com.example.banking.models.Account;
import com.example.banking.models.User;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.repositories.UserRepository;
import com.example.banking.services.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

@Service
@RequiredArgsConstructor
@EnableTransactionManagement
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
        account.setActivate(true);
        return accountRepository.save(account);
    }

    @Override
    public Account createAccount(User user) {
        return null;
    }
    @Override
    public Account createAccount(User user, String accountName) {
        Account account = new Account();
        account.setAmount(0.0);
        account.setUser(user);
        account.setActivate(true);
        account.setName(accountName);
        return accountRepository.save(account);
    }

    @Override
    public Account activateAccount(Account account) {
        account.setActivate(true);
        return accountRepository.save(account);
    }
    @Override
    public Account deactivateAccount(Account account) {
        account.setActivate(false);
        return accountRepository.save(account);
    }
    @Override
    public Account changeStateAccount(Account account) {
        if(account.getActivate()){
            return deactivateAccount(account);
        }else{
            return activateAccount(account);
        }
    }
    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        return accountRepository.findAllByUserId(userId);
    }
    @Override
    public Account addMoney(Double money, Account account) {
        account.setAmount(account.getAmount() + money);
        return accountRepository.save(account);
    }
    @Override
    public Account setMoney(Double money, Account account) {
        account.setAmount(money);
        return accountRepository.save(account);
    }

//    @Override
////    @Transactional(value = Transactional.TxType.REQUIRED)
//    @Transactional

//    public synchronized List<Account> transfer(Double money,Account accountFrom,Account accountTo) {
//        List<Account> accounts = new ArrayList<>();
//
//        accountFrom = accountRepository.findById(accountFrom.getId()).orElse(null);
//        accountTo = accountRepository.findById(accountTo.getId()).orElse(null);
//
//        if(accountFrom!=null&&accountTo!=null){
//            accountFrom.setAmount(accountFrom.getAmount() - money);
//            accounts.add(accountRepository.save(accountFrom));
//                //imitationException();
//            accountTo.setAmount(accountTo.getAmount() + money);
//            accounts.add(accountRepository.save(accountTo));
//        }else{
//            System.out.println("перевод не был осуществлён так как не удалось распознать один из оккаунтов");
//        }
//
//        return accounts;
//    }

    @Override
    public synchronized List<Account> transfer(Double money,Account accountFrom,Account accountTo) {
        accountRepository.transfer(accountFrom.getId(),accountTo.getId(),money);

        List<Account> accounts = new ArrayList<>();
        accountFrom = accountRepository.findById(accountFrom.getId()).orElse(null);
        accountTo = accountRepository.findById(accountTo.getId()).orElse(null);
        accounts.add(accountFrom);
        accounts.add(accountTo);

        return accounts;
    }

    private void imitationException(){
        throw new RuntimeException();
    }

}
