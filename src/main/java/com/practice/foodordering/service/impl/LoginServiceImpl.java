package com.practice.foodordering.service.impl;

import com.practice.foodordering.repository.LoginRepository;
import com.practice.foodordering.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public void loginUser(String userNumber) {
        boolean isUserLoggedIn = loginRepository.isUserLoggedIn(userNumber);
        if(isUserLoggedIn){return;}
        loginRepository.login(userNumber);
    }

    @Override
    public String getLoggedInUser() {
        return loginRepository.getLoggedInUser();
    }
}
