package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.components.CookieManager;
import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.services.UserService;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("admin/user")
public class UserController {
    @Autowired
    CookieManager cookieManager;
    @Autowired
    UserService userService;
    @Autowired
    ConfProperties properties;

    @GetMapping("/list")
    public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("main");

        List<User> users = userService.getAllUsers();

        modelAndView.addObject("users", users);

        modelAndView.addObject("content", "user/list");
        return modelAndView;
    }
}
