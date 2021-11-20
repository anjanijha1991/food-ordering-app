package com.practice.foodordering.repository;

import com.practice.foodordering.dto.Login;
import com.practice.foodordering.exception.NoUserLoggedInException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginRepository {

    Login currentUser;
    List<Login> loginDetails;

    public boolean isUserLoggedIn(String userNumber) {
        return currentUser != null && currentUser.getUser().equals(userNumber);
    }

    public String getLoggedInUser() {
        if (currentUser == null) {
            throw new NoUserLoggedInException();
        }
        return currentUser.getUser();
    }

    public void login(String userNumber) {
        if (currentUser != null) {
            currentUser.setLoggedInTill(System.currentTimeMillis());
            loginDetails.add(currentUser);
        }
        currentUser = new Login();
        currentUser.setUser(userNumber);
        currentUser.setLoggedInFrom(System.currentTimeMillis());
    }


}
