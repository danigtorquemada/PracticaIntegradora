package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    LocalDate lockUserByUsername(String username);
    LocalDate lockUser(Long userId, Integer days);
    LocalDate lockUser(Long userId, Integer days, UserAdmin userAdmin);
    boolean unlockUser(Long userId);

    boolean unlockUser(Long userId, UserAdmin userAdmin);

    boolean isLocked(Long userId);

    boolean isRemoved(Long userId);

    boolean isCorrectUser(UserAuthentication userAuthentication);
    User findByUsername(String username);
    User save(User user);

    LocalDate removeUser(Long userId, UserAdmin userAdmin);
    boolean recoverUser(Long userId);
}
