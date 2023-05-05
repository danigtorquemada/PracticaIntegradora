package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.services.UserService;
import com.dgomezt.practicaintegradora.services.UserServiceImpl;
import com.dgomezt.practicaintegradora.utilities.UserForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/form")
    public ModelAndView printForm(@ModelAttribute("errorUnicidad") String error) {
        ModelAndView mAV = new ModelAndView();
        mAV.setViewName("main");

        UserForm userForm = new UserForm();

        if(!error.isEmpty()) userForm.setGlobalErrors(error);

        mAV.addObject(userForm);
        mAV.addObject("contenido", "user/signUp");
        return mAV;
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(@Valid UserForm userForm, BindingResult bindingResult) {
        ModelAndView mAV = new ModelAndView();

        if (bindingResult.hasErrors()) {
            if (bindingResult.hasGlobalErrors()) {
                for (ObjectError globalError : bindingResult.getGlobalErrors()){
                    if (globalError.getCode().equals("MatchValues"))
                        bindingResult.rejectValue("errorMatchPwds", "MatchValues", bindingResult.getGlobalError().getDefaultMessage());
                }
            }

            mAV.setViewName("main");
            mAV.addObject("contenido", "user/signUp");
            return mAV;
        }

        User newUser = new User();
        newUser.setEmail(userForm.getUsername());
        newUser.setPassword(userForm.getPassword());
        userService.guardar(newUser);

        mAV.setViewName("logged");
        return mAV;
    }
}
