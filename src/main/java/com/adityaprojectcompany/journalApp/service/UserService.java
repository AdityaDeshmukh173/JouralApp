package com.adityaprojectcompany.journalApp.service;

import com.adityaprojectcompany.journalApp.Entity.User;
import com.adityaprojectcompany.journalApp.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository ;

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public boolean saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user) ;
            return true ;
        } catch (Exception e) {
            log.error("hahahaha");
            log.warn("hahahaha");
            log.info("hahahaha");
            log.debug("hahahaha");
            log.trace("hahahaha");
            return false ;
        }
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER","ADMIN"));
        userRepository.save(user) ;
    }

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;

    public void saveUser(User user){
        userRepository.save(user) ;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id) ;
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName) ;
    }
}

//  controller --(call)--> service --(call)--> repository