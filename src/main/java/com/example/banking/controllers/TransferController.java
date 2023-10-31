package com.example.banking.controllers;

import com.example.banking.models.User;
import com.example.banking.services.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/banking")
@RequiredArgsConstructor
public class TransferController {
    private final AccountServiceImpl accountService;

    @GetMapping("/add_money")
    public String addMoney(Principal principal,Model model){
//        User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
//        accountService.addMoney(money,user.getId());
//
//        model.addAttribute("user",userService.getById(user.getId()));
        return "user/menu";
    }

    @PostMapping("/add_money")
    public String addMoney(Double money,Long accountId,Model model){
        accountService.addMoney(money,accountId);
        model.addAttribute("account",accountService.getById(accountId));
        return "user/account";
    }

    @GetMapping("/transfer")
    public String userMenu(Principal principal, Model model){
        User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
//        model.addAttribute("user",userService.getById(user.getId()));
        return "user/menu";
    }
}
