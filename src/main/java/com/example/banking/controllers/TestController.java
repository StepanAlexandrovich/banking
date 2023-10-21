package com.example.banking.controllers;

import com.example.banking.models.User;
import com.example.banking.models.UserAndRole;
import com.example.banking.models.UserRole;
import com.example.banking.repositories.UserRoleRepository;
import com.example.banking.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TestController {
    private final UserServiceImpl userService;
    private final UserRoleRepository userRoleRepository;

    @GetMapping("/test")
    public String test(){
//        UserCreateDto userCreateDto = new UserCreateDto("admin","1");
//        userService.createUser(userCreateDto);

        System.out.println("---------------------");


//        for (User user : userService.getAll()) {
        for (User user : userService.getAllByUserRoleId(1L)) {
            System.out.println(user.getLogin());

            for (UserAndRole userAndRole : user.getUserAndRoles()) {
                UserRole userRole = userRoleRepository.findById(userAndRole.getId().getUserRoleId()).orElse(null);
                System.out.println(userRole.getRole());
            }
        }

        return "test";
    }

    @GetMapping("/test1")
    public String test1(){
        return "test1";
    }
}
