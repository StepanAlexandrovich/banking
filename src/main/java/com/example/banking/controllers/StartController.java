package com.example.banking.controllers;

import com.example.banking.dto.UserCreateDto;
import com.example.banking.services.impl.UserServiceImpl;
import com.example.banking.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class StartController {
    private final UserValidation userValidation;
    private final UserServiceImpl userService;

    @GetMapping("/login")
    public String login(Principal principal){

        System.out.println(principal);
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userCreateDto",new UserCreateDto());
        return "registration";
    }
    @PostMapping("/registration")
    public String registrationClient(@Valid UserCreateDto userCreateDto, BindingResult bindingResult, Model model){
        userValidation.validate(userCreateDto,bindingResult);
        if(!bindingResult.hasErrors()){
            model.addAttribute("noErrors",true);
            userService.createUser(userCreateDto);
            return "login";
        }
        model.addAttribute("userCreateDto",userCreateDto);
        return "registration";
    }

    @GetMapping("/start")
    public String start(Principal principal){

        return "start";
    }


}
