package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;

public interface UserService {
    boolean isCorrectUser(UserAuthentication userAuthentication);
    User findByUsername(String username);
    void save(User user);
}
