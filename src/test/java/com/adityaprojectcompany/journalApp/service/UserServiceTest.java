//package com.adityaprojectcompany.journalApp.service;
//
//import com.adityaprojectcompany.journalApp.Entity.User;
//import com.adityaprojectcompany.journalApp.Repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.util.AssertionErrors.assertTrue;
//
//@SpringBootTest
//public class UserServiceTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @BeforeEach
//    void setup(){
//
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings ={
//            "ram",
//            "shyam",
//            "vipul"
//    })
//    public void testfindByUserName(String name){
//        assertNotNull(userRepository.findByUserName(name),"failed for "+name);
//    }
//
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2,10,12",
//            "3,3,9"
//    })
//    public void test(int a, int b, int expected){
//        assertEquals(expected, a+b);
//    }
//
//
//
//}
