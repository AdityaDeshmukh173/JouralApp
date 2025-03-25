package com.adityaprojectcompany.journalApp.controller;

import com.adityaprojectcompany.journalApp.Entity.User;
import com.adityaprojectcompany.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService ;

    @PostMapping("/create-user")
    public void createEntry(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok" ;
    }
}
