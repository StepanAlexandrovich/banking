package com.example.banking.controllers.transfer;

import com.example.banking.models.Account;
import com.example.banking.services.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final AccountServiceImpl accountService;

//    @PostMapping("/accounts")
//    public String getAccounts(Model model){
//        List<Account> accounts = accountService.getAll();
//        accounts.sort((o1, o2) -> (int)(o1.getId() - o2.getId()));
//
//        model.addAttribute("accounts",accounts);
//        return "accounts/all_accounts";
//    }

    @GetMapping("/accounts")
    public String getAccounts(Model model){
        List<Account> accounts = accountService.getAll();
        accounts.sort((o1, o2) -> (int)(o1.getId() - o2.getId()));

        model.addAttribute("accounts",accounts);
        return "accounts/all_accounts";
    }

    @PostMapping("/add_money")
    public String addMoney(Double money,Long accountId,Model model){
        accountService.addMoney(money,accountService.getById(accountId));
        model.addAttribute("account",accountService.getById(accountId));
        return "user/account";
    }

    @PostMapping("/transfer_money")
    public String transferMoney(Double money,Long accountIdFrom,Long accountIdTo,Model model){
        Account from = accountService.getById(accountIdFrom);
        Account to = accountService.getById(accountIdTo);

        accountService.transfer(money,from,to);

//        final Account[] account = new Account[1];
//        Thread thread = new Thread(() -> account[0] = accountService.getById(accountIdFrom));
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(account[0]);


//        return "forward:/transfer/accounts";
        return "redirect:/transfer/accounts";

    }

}
