package com.example.banking.controllers;

import com.example.banking.services.impl.AccountServiceImpl;
import com.example.banking.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    private final UserServiceImpl userService;
    private final AccountServiceImpl accountService;

    @GetMapping("/menu")
    public String adminMenu(){
        return "admin/menu";
    }
    @GetMapping("/users")
    public String getUsers(Model model){
        model.addAttribute("users",userService.getAll());
        return "users";
    }
    @PostMapping("/add_account")
    public String addAccount(Long userId){
        accountService.createAccount(userId);
        return "users";
    }
}
