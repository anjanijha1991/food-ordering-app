package com.practice.foodordering.resource;

import com.practice.foodordering.dto.Gender;
import com.practice.foodordering.dto.User;
import com.practice.foodordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    ResponseEntity registerUser(@RequestParam("name") String name, @RequestParam("gender") Gender gender,
                                @RequestParam("phone_number") String phoneNumber, @RequestParam("pincode") String pinCode) {

        try {
            userService.registerUser(User.builder().gender(gender).name(name).phoneNumber(phoneNumber).pin(pinCode).build());
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            System.out.println("error registering user " + e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
