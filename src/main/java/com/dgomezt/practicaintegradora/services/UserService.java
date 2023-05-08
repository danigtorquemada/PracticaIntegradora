package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    LocalDate lockUserAuthentication(String username);
    LocalDate lockUser(Long userId);
    LocalDate unlockUser(Long userId);
    boolean isLocked(Long userId);
    boolean isCorrectUser(UserAuthentication userAuthentication);
    User findByUsername(String username);
    void save(User user);
}
