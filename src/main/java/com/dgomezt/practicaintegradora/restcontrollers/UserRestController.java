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
    public boolean unlockUser(Long userId) {

        return userService.unlockUser(userId);
    }
    @GetMapping("remove")
    public LocalDate removeUser(Long userId){
        return userService.removeUser(userId);
    }

    @GetMapping("recover")
    public boolean recoverUser(Long userId){
        return userService.recoverUser(userId);
    }
}
