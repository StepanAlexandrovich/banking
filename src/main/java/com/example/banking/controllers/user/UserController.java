package com.example.banking.controllers.user;

import com.example.banking.models.User;
import com.example.banking.services.impl.AccountServiceImpl;
import com.example.banking.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @GetMapping("/menu")
    public String userMenu(Principal principal, Model model){
        User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        model.addAttribute("user",userService.getById(user.getId()));
        return "user/menu";
    }
    @PostMapping("/accounts")
    public String userAccounts(Long userId,Model model){
        model.addAttribute("user",userService.getById(userId));
        return "user/accounts";
    }
    @GetMapping("/account/{accountId}")
    public String userAccount(@PathVariable Long accountId, Model model){
        model.addAttribute("account",accountService.getById(accountId));
        return "user/account";
    }
    @PostMapping("/images")
    public String userImages(Long userId, Model model){
        model.addAttribute("user",userService.getById(userId));
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
        userService.addImageToUserByUserId(files,userId);
        return userImages(userId,model);
    }
}
