package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.services.UserService;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import com.dgomezt.practicaintegradora.utilities.UserForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class SignUpController {
    @Autowired
    ConfProperties properties;
    @Autowired
    UserService userService;

    @GetMapping("/signUp")
    public ModelAndView printForm() {
        ModelAndView modelAndView = new ModelAndView();

        UserForm userForm = new UserForm();

        modelAndView.addObject(userForm);
        modelAndView.setViewName("main");
        modelAndView.addObject(properties.CONTENT_CONTAINER, "user/signUp");
        return modelAndView;
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(@Valid UserForm userForm, BindingResult bindingResult, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject(properties.CONTENT_CONTAINER, "user/signUp");
            return modelAndView;
        }

        if (userService.findByUsername(userForm.getUsername()) != null) {
            ObjectError error = new ObjectError("globalError", "Ya existe el usuario.");
            bindingResult.addError(error);

            modelAndView.addObject(properties.CONTENT_CONTAINER, "user/signUp");
            return modelAndView;
        }

        User newUser = new User();
        newUser.setEmail(userForm.getUsername());
        newUser.setPassword(userForm.getPassword());
        User user = userService.save(newUser);

        httpSession.setAttribute(properties.SESSION_USER, user);
        modelAndView.setViewName("redirect:/user/logged");
        return modelAndView;
    }
}
