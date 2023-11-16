package com.example.banking.transfer;

import com.example.banking.models.Account;
import com.example.banking.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransferTest {
    @Autowired
    private AccountServiceImpl accountService;

    public Account from() { return accountService.getById(32L); }
    public Account to()   { return accountService.getById(33L);   }

    @Test
    void transferTest(){
        accountService.setMoney(100.0,from());
        accountService.setMoney(100.0,to());

        new ThreadsHelper().startFinish(() -> accountService.transfer(1.0,from(),to()),100);

        Assertions.assertEquals(0.0,  from().getAmount());
        Assertions.assertEquals(200.0,to().getAmount());
    }

}
