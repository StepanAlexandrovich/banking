package com.example.banking;

import com.example.banking.models.Account;
import com.example.banking.services.AccountService;
import com.example.banking.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user1")
public class MyTest {
    @Autowired
    AccountServiceImpl accountService;

    @Test
    void test(){
        Long accountIdFrom = 32L;
        Long accountIdTo = 33L;
        Double money = 100.0;

        Account from = accountService.getById(accountIdFrom);
        Account to = accountService.getById(accountIdTo);

        Thread thread = new Thread(() -> accountService.transfer(money,from,to));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
        //Assertions.assertEquals(0.0,  accountService.getById(accountIdFrom).getAmount());
        //Assertions.assertEquals(200.0,accountService.getById(accountIdTo).getAmount());
    }
}
