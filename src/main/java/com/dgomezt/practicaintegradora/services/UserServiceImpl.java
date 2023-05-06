package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.repositories.UserRepository;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isCorrectUser(UserAuthentication userAuthentication) {
        Optional<User> user = userRepository.findByEmail(userAuthentication.getUsername());
        if(user.isEmpty()) return false;

        return user.get().getPassword().equals(userAuthentication.getPassword());
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        return user.isEmpty() ? null : user.get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
