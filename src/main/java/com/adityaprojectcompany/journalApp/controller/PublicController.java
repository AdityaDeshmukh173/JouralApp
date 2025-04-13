package com.adityaprojectcompany.journalApp.controller;

import com.adityaprojectcompany.journalApp.Entity.User;
import com.adityaprojectcompany.journalApp.service.UserService;
import com.adityaprojectcompany.journalApp.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService ;

    @Autowired
    private UserService userService ;

    @Autowired
    private JwtUtil jwtUtil ;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok" ;
    }

//    @PostMapping("/create-user")
//    public void createEntry(@RequestBody User user){
//        userService.saveNewUser(user);
//    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword())) ;
            UserDetails userDetail = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetail.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occured while Create-Authentication-Token", e);
            return new ResponseEntity<>("Incorrect Username or Password" , HttpStatus.BAD_REQUEST) ;
        }
    }

}
