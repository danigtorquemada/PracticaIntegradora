package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequestMapping("/user")
@RestController
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping("/password_user")
    public String recoverPassword(String user) {
        return userService.findByUsername(user).getPassword();
    }

    @GetMapping("/lock")
    public LocalDate lockUser(Long userId, Integer daysLock) {

        return userService.lockUser(userId,daysLock);
    }

    @GetMapping("/unlock")
    public LocalDate unlockUser(String userId) {
        long id = Long.parseLong(userId);

        return userService.unlockUser(id);
    }
}
