package com.practice.foodordering.resource;

import com.practice.foodordering.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
public class LoginResource {

    @Autowired
    LoginService loginService;

    @PostMapping("/user/{user_number}")
    ResponseEntity registerUser(@PathVariable("user_number") String userNumber) {

        try {
            loginService.loginUser(userNumber);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            System.out.println("error registering user " + e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
