//package com.adityaprojectcompany.journalApp.service;
//
//import com.adityaprojectcompany.journalApp.Entity.User;
//import com.adityaprojectcompany.journalApp.Repository.UserRepository;
//import com.adityaprojectcompany.journalApp.service.UserDetailsServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//
//import java.util.ArrayList;
//import static org.mockito.Mockito.*;
//
//
//
//@ActiveProfiles("dev")
//@SpringBootTest
////@ExtendWith(MockitoExtension.class)
//public class UserDetailsServiceImplTests {
//
//    @InjectMocks
//    private UserDetailsServiceImpl userDetailsService ;
//
//    @Mock
//    private UserRepository userRepository ;
//
//    @BeforeEach
//    void setup(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void loadUserByUsernameTest(){
//        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("olansk").roles(new ArrayList<>()).build());
//        UserDetails user = userDetailsService.loadUserByUsername("ram");
//        Assertions.assertNotNull(user);
//    }
//
//}
