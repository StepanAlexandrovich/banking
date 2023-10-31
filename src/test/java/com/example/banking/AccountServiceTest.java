package com.example.banking;

import com.example.banking.models.Account;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.repositories.UserRepository;
import com.example.banking.services.AccountService;
import com.example.banking.services.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
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
        Mockito.when(accountRepository.findById(15L)).thenReturn(Optional.of(new Account(15L,44.0,null)));
        AccountService accountService = new AccountServiceImpl(accountRepository,userRepository);
        Account account = accountService.getById(15L);

        Assertions.assertEquals(44.0,account.getAmount(),"сходится");
    }
}
