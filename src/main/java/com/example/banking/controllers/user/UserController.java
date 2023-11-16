package com.example.banking.controllers.user;

import com.example.banking.models.Account;
import com.example.banking.models.User;
import com.example.banking.services.impl.AccountServiceImpl;
import com.example.banking.services.impl.UserImageServiceImpl;
import com.example.banking.services.impl.UserServiceImpl;
import com.example.banking.validation.AccountValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final AccountServiceImpl accountService;
    private final UserImageServiceImpl imageService;
    private final AccountValidation accountValidation;
    private User getCurrentUser(Principal principal){
        User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        return userService.getById(user.getId());
    }
    @GetMapping("/menu")
    public String userMenu(Principal principal, Model model){
        User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        model.addAttribute("user",userService.getById(user.getId()));
        return "user/menu";
    }
    @PostMapping("/accounts")
    public String userAccounts(Principal principal,Model model){
        model.addAttribute("user",getCurrentUser(principal));
        model.addAttribute("account",new Account());
        return "accounts/user_accounts";
    }
    @GetMapping("/account/{accountId}")
    public String userAccount(@PathVariable Long accountId, Model model){
        model.addAttribute("account",accountService.getById(accountId));
        Account account = (Account) model.getAttribute("account");
        return "accounts/account";
    }

    @PostMapping("/add_account")
    public String addAccount(Principal principal, Account account, BindingResult bindingResult, Model model){
        User user = getCurrentUser(principal);

        accountValidation.validate(account,bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("account",account);
            model.addAttribute("user",getCurrentUser(principal));
            return "accounts/user_accounts";
            //return "forward:/user/accounts";
        }


        accountService.createAccount(user,account.getName());
        return "forward:/user/accounts";
    }

    @PostMapping("/images")
    public String userImages(Principal principal, Model model){
        model.addAttribute("user",getCurrentUser(principal));
        return "user/images";
    }
    @PostMapping("/add_images")
    public String addImages(Long userId,
                             @RequestParam MultipartFile file,
                            Principal principal,
                            Model model
    ){  // переделать
        List<MultipartFile> files = new ArrayList<>();
        files.add(file);
        imageService.addImageToUserByUserId(files,userId);
        return "forward:/user/images";
    }



    @GetMapping("/test_redirect")
    public String testRedirect(Principal principal, Model model){
        return "redirect:/user/test_redirected";
    }

    @GetMapping("/test_redirected")
    public String testRedirected(Principal principal, Model model){
        return "test";
    }
}
