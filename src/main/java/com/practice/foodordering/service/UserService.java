package com.practice.foodordering.service;

import com.practice.foodordering.dto.User;

public interface UserService {
    void registerUser(User build);

    User getUser(String loggedInUser);
}
