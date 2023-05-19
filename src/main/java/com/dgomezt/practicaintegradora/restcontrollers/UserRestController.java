package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.services.UserService;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping("/user")
@RestController
public class UserRestController {
    @Autowired
    UserService userService;
    @Autowired
    ConfProperties confProperties;

    @GetMapping("/password_user")
    public String recoverPassword(String user) {
        return userService.findByUsername(user).getPassword();
    }

    @GetMapping("/lock")
    public LocalDate lockUser(Long userId, Integer daysLock, HttpSession httpSession) {
        UserAdmin userAdmin = (UserAdmin) httpSession.getAttribute(confProperties.SESSION_ADMIN_USER);
        return userService.lockUser(userId, daysLock, userAdmin);
    }

    @GetMapping("/unlock")
    public boolean unlockUser(Long userId, HttpSession httpSession) {
        UserAdmin userAdmin = (UserAdmin) httpSession.getAttribute(confProperties.SESSION_ADMIN_USER);
        return userService.unlockUser(userId, userAdmin);
    }

    @GetMapping("remove")
    public LocalDate removeUser(Long userId, HttpSession httpSession) {
        UserAdmin userAdmin = (UserAdmin) httpSession.getAttribute(confProperties.SESSION_ADMIN_USER);
        return userService.removeUser(userId, userAdmin);
    }

    @GetMapping("recover")
    public boolean recoverUser(Long userId, HttpSession httpSession) {
        UserAdmin userAdmin = (UserAdmin) httpSession.getAttribute(confProperties.SESSION_ADMIN_USER);
        return userService.recoverUser(userId);
    }
}
