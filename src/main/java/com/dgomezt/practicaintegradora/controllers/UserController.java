package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.components.CookieManager;
import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.services.UserService;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;
import com.dgomezt.practicaintegradora.utilities.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String COOKIE_LAST_USER = "_lastUser";
    private static final String COOKIE_CONNECTED_USER = "_connectedUser";

    private static final String CONTENT_CONTAINER = "content";
    private static final String FRAGMENT_CONTAINER = "fragment";

    @Autowired
    CookieManager cookieManager;
    @Autowired
    UserService userService;

    @GetMapping("/signUp")
    public ModelAndView printForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        UserForm userForm = new UserForm();

        modelAndView.addObject(userForm);
        modelAndView.addObject(CONTENT_CONTAINER, "user/signUp");
        return modelAndView;
    }

    @PostMapping("/signUp")
    public ModelAndView signUp(@Valid UserForm userForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("main");
            modelAndView.addObject(CONTENT_CONTAINER, "user/signUp");
            return modelAndView;
        }

        if (userService.findByUsername(userForm.getUsername()) != null) {
            ObjectError error = new ObjectError("globalError", "Ya existe el usuario.");
            bindingResult.addError(error);

            modelAndView.setViewName("main");
            modelAndView.addObject(CONTENT_CONTAINER, "user/signUp");
            return modelAndView;
        }

        User newUser = new User();
        newUser.setEmail(userForm.getUsername());
        newUser.setPassword(userForm.getPassword());
        userService.save(newUser);

        modelAndView.setViewName("logged");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView logIn(@ModelAttribute UserAuthentication user,
                              @CookieValue(value = COOKIE_LAST_USER, required = false) String lastUser,
                              @CookieValue(value = COOKIE_CONNECTED_USER, required = false) String connectedUser,
                              HttpServletRequest httpServletRequest,
                              HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", user);

        modelAndView.setViewName("main");
        modelAndView.addObject(CONTENT_CONTAINER, "user/logIn");
        modelAndView.addObject(FRAGMENT_CONTAINER, "username");
        return modelAndView;
    }

    @GetMapping("/login/username")
    public ModelAndView logInUsername(@ModelAttribute UserAuthentication user, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        if(session.getAttribute("user") != null)
            user = (UserAuthentication) session.getAttribute("user");
        if(session.getAttribute("error") != null)
            modelAndView.addObject("error", session.getAttribute("error"));

        modelAndView.addObject("user", user);

        modelAndView.setViewName("main");
        modelAndView.addObject(CONTENT_CONTAINER, "user/logIn");
        modelAndView.addObject(FRAGMENT_CONTAINER, "username");
        return modelAndView;
    }

    @PostMapping("/login/username")
    public ModelAndView postUsername(@ModelAttribute UserAuthentication user,
                                     HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        session.setAttribute("user", user);

        if(userService.findByUsername(user.getUsername()) == null) {
            session.setAttribute("error", "El usuario no se encuentra en la base de datos");
            modelAndView.setViewName("redirect:/user/login/username");
            return modelAndView;
        }

        session.removeAttribute("error");

        modelAndView.setViewName("redirect:/user/login/password");
        return modelAndView;
    }

    @GetMapping("/login/password")
    public ModelAndView logInPassword(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        UserAuthentication user = (UserAuthentication) session.getAttribute("user");
        modelAndView.addObject("user", user);

        modelAndView.addObject("errorPwd", session.getAttribute("errorPwd"));

        modelAndView.setViewName("main");
        modelAndView.addObject(CONTENT_CONTAINER, "user/logIn");
        modelAndView.addObject(FRAGMENT_CONTAINER, "password");
        return modelAndView;
    }

    @PostMapping("/login/password")
    public ModelAndView postPassword(@ModelAttribute UserAuthentication user,
                                     HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        UserAuthentication userSession = (UserAuthentication) session.getAttribute("user");
        user.setUsername(userSession.getUsername());

        if(userService.isCorrectUser(user)) {
            session.removeAttribute("errorPwd");
            modelAndView.setViewName("logged");
            return modelAndView;
        }

        session.setAttribute("errorPwd", "Contraseña incorrecta");

        modelAndView.setViewName("redirect:/user/login/password");
        return modelAndView;
    }
}
