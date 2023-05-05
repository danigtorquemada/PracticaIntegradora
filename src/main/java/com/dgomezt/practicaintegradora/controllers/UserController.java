package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.services.UserService;
import com.dgomezt.practicaintegradora.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/form")
    public ModelAndView form(){
        ModelAndView mAV = new ModelAndView();
        mAV.setViewName("main");

        mAV.addObject("contenido", "user/signUp");
        return mAV;
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmPwd){
        User newUser = new User();

        newUser.setEmail(username);
        newUser.setPassword(password);
        userService.guardar(newUser);

        return new ModelAndView("logged");
    }
}
