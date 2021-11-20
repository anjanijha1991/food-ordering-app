package com.practice.foodordering.repository;

import com.practice.foodordering.dto.User;
import com.practice.foodordering.exception.DataAlreadyExistsException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {
    Map<String, User> userDetails = new HashMap<>();

    public void registerUser(User user) {
        if (userDetails.containsKey(user.getPhoneNumber())) {
            throw new DataAlreadyExistsException("User Already Exists!!");
        }
        userDetails.put(user.getPhoneNumber(), user);

    }

    public User getUser(String user) {
        return userDetails.get(user);
    }
}
