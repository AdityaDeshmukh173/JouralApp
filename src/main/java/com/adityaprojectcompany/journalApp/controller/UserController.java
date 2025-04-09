package com.adityaprojectcompany.journalApp.controller;

import com.adityaprojectcompany.journalApp.Entity.User;
import com.adityaprojectcompany.journalApp.Repository.UserRepository;
import com.adityaprojectcompany.journalApp.api.response.WeatherResponse;
import com.adityaprojectcompany.journalApp.service.UserService;
import com.adityaprojectcompany.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private WeatherService weatherService ;

//    @GetMapping
//    public List<User> getAllUsers(){                  For Admin Only
//        return userService.getAll() ;
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userService.saveNewUser(userInDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "" ;
        if (weatherResponse != null){
            greeting = " Weather Feels Like "+weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hii "+ authentication.getName() +greeting, HttpStatus.OK);
    }


}
