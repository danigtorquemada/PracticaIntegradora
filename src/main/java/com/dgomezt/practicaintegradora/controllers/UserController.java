package com.dgomezt.practicaintegradora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/signUp")
    public ModelAndView signUp(){
        ModelAndView mAV = new ModelAndView();
        mAV.setViewName("main");

        mAV.addObject("contenido", "fragments/user/signUp");
        return mAV;
    }
}
