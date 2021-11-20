package com.practice.foodordering.service.impl;

import com.practice.foodordering.dto.User;
import com.practice.foodordering.repository.UserRepository;
import com.practice.foodordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void registerUser(User user) {
        userRepository.registerUser(user);
    }

    @Override
    public User getUser(String loggedInUser){
        return userRepository.getUser(loggedInUser);
    }
}
