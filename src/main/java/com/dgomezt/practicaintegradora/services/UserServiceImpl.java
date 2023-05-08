package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.repositories.UserRepository;
import com.dgomezt.practicaintegradora.utilities.Constants;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public LocalDate lockUserAuthentication(String username) {
        Optional<User> userOptional = userRepository.findByEmail(username);

        return lockUser(userOptional.get().getId());
    }

    @Override
    public LocalDate lockUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        user.setLockDate(LocalDate.now().plusDays(Constants.LOCK_DAYS));

        save(user);

        return user.getLockDate();
    }

    @Override
    public LocalDate unlockUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        user.setLockDate(null);

        save(user);

        return user.getLockDate();
    }

    @Override
    public boolean isLocked(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        LocalDate lockDate = user.getLockDate();

        if(lockDate == null) return false;

        return lockDate.isBefore(LocalDate.now());
    }

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
