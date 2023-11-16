package com.example.banking.controllers.admin;

import com.example.banking.models.Account;
import com.example.banking.models.User;
import com.example.banking.services.impl.AccountServiceImpl;
import com.example.banking.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Comparator;
import java.util.List;

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
        List<User> users = userService.getAll();
        users.sort(Comparator.comparing(User::getLogin));

        model.addAttribute("users",users);
        return "admin/users";
    }

//    @PostMapping("/accounts")
//    public String getAccounts(Model model){
//        List<Account> accounts = accountService.getAll();
//        accounts.sort((o1, o2) -> (int)(o1.getId() - o2.getId()));
//
//        model.addAttribute("accounts",accounts);
//        return "accounts/all_accounts";
//    }
    @PostMapping("/change_state_account")
    public String changeStateAccount(Long accountId,Model model){
        accountService.changeStateAccount(accountService.getById(accountId));
        return "forward:/admin/accounts";
    }

}
