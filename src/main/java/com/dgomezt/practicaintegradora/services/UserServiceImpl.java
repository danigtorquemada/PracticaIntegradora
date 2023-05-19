package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.repositories.UserRepository;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfProperties properties;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public LocalDate lockUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByEmail(username);

        return lockUser(userOptional.get().getId(), properties.LOCK_DAYS);
    }

    @Override
    public LocalDate lockUser(Long userId, Integer days) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        user.setLockDate(LocalDate.now().plusDays(days));

        save(user);

        return user.getLockDate();
    }

    @Override
    public LocalDate lockUser(Long userId, Integer days, UserAdmin userAdmin) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        user.setLockDate(LocalDate.now().plusDays(days));
        user.getAuditory().setLastModificationDate(LocalDate.now());
        user.getAuditory().setLastModificationUser(userAdmin);

        save(user);

        return user.getLockDate();
    }

    @Override
    public boolean unlockUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        user.setLockDate(null);

        save(user);

        return true;
    }

    @Override
    public boolean unlockUser(Long userId, UserAdmin userAdmin) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        user.setLockDate(null);
        user.getAuditory().setLastModificationUser(userAdmin);
        user.getAuditory().setLastModificationDate(LocalDate.now());

        save(user);

        return true;
    }

    @Override
    public boolean isLocked(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        LocalDate lockDate = user.getLockDate();

        if(lockDate == null) return false;

        return lockDate.isAfter(LocalDate.now());
    }

    @Override
    public boolean isRemoved(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        if(user.getAuditory() == null) return false;

        return user.getAuditory().getRemovedDate() != null;
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
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public LocalDate removeUser(Long userId, UserAdmin userAdmin) {
        Optional<User> userOptional = userRepository.findById(userId);

        User user = userOptional.get();
        LocalDate removedDate = LocalDate.now();
        user.getAuditory().setRemovedDate(removedDate);
        user.getAuditory().setRemovedUser(userAdmin);

        save(user);

        return removedDate;
    }

    @Override
    public boolean recoverUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        User user = userOptional.get();
        user.getAuditory().setRemovedDate(null);
        user.getAuditory().setRemovedUser(null);

        save(user);

        return true;
    }
}
