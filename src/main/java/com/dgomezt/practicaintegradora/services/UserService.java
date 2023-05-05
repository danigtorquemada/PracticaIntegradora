package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.User;

public interface UserService {
    User findByUsername(String username);
    void save(User user);
}
