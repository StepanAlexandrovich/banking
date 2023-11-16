package com.example.banking;

import com.example.banking.models.Account;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.repositories.UserRepository;
import com.example.banking.services.AccountService;
import com.example.banking.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    public void checkBalance(){
        Mockito.when(accountRepository.findById(15L)).thenReturn(Optional.of(new Account(15L,44.0,null,null,null)));
        AccountService accountService = new AccountServiceImpl(accountRepository,userRepository);
        Account account = accountService.getById(15L);

        Assertions.assertEquals(44.0,account.getAmount(),"не сходится");
    }

    @Test
    public void checkBalanceAddMoney(){
        Mockito.when(accountRepository.findById(15L)).thenReturn(Optional.of(new Account(15L,2.0,null,null,null)));
        AccountService accountService = new AccountServiceImpl(accountRepository,userRepository);

        Account account = accountService.getById(15L);
        accountService.addMoney(2.0, account);

        Assertions.assertEquals(4.0,account.getAmount(),"не сходится");
    }

    @Test
    public void checkBalanceTransferMoney(){
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account(1L,2.0,null,null,null)));
        Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(new Account(2L,2.0,null,null,null)));
        AccountService accountService = new AccountServiceImpl(accountRepository,userRepository);

        Account accountFrom = accountService.getById(1L);
        Account accountTo = accountService.getById(2L);
        accountService.transfer(2.0,accountFrom,accountTo);

        Assertions.assertEquals(0.0,accountFrom.getAmount(),"не сходится");
        Assertions.assertEquals(4.0,accountTo.getAmount(),"не сходится");
        Assertions.assertNotEquals(1.0,accountFrom.getAmount(),"не сходится");
    }
}
