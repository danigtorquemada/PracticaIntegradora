package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void lockUser(UserAuthentication userAuthentication);
    boolean isCorrectUser(UserAuthentication userAuthentication);
    User findByUsername(String username);
    void save(User user);
}
