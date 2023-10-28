package com.example.banking.controllers;

import com.example.banking.models.User;
import com.example.banking.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/menu")
    public String userMenu(Principal principal, Model model){
        User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        model.addAttribute("user",userService.getById(user.getId()));
        return "user/menu";
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
        return userMenu(principal,model);
    }
}
