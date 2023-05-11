package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.components.CookieManager;
import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.services.UserService;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;
import com.dgomezt.practicaintegradora.utilities.UserForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    CookieManager cookieManager;
    @Autowired
    UserService userService;
    @Autowired
    ConfProperties properties;

    @ModelAttribute("idSession")
    public String addIdSessionHTML(HttpSession session){
        return session.getId();
    }

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
    public ModelAndView signUp(@Valid UserForm userForm, BindingResult bindingResult) {
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
        userService.save(newUser);

        modelAndView.addObject("user", userForm);
        modelAndView.addObject("content", "user/logged");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView logIn(@ModelAttribute UserAuthentication user,
                              @CookieValue(value = "${custom.COOKIE_LAST_USER}", required = false) String lastUser,
                              @CookieValue(value = "${custom.COOKIE_CONNECTED_USER}", required = false) String connectedUser,
                              HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();


        if (connectedUser != null) {
            String username = cookieManager.readEncodedCookie(connectedUser);
            user.setUsername(username);
            session.setAttribute(properties.SESSION_USER, user);

            modelAndView.setViewName("redirect:/user/logged");
            return modelAndView;
        }
        if (lastUser != null) {
            String username = cookieManager.readEncodedCookie(lastUser);
            user.setUsername(username);
            session.setAttribute(properties.SESSION_USER, user);

            modelAndView.setViewName("redirect:/user/login/password");
            return modelAndView;
        }

        session.setAttribute(properties.SESSION_USER, user);

        modelAndView.setViewName("redirect:/user/login/username");
        return modelAndView;
    }

    @GetMapping("/login/username")
    public ModelAndView logInUsername(@ModelAttribute UserAuthentication user, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        if (session.getAttribute(properties.SESSION_USER) != null)
            user = (UserAuthentication) session.getAttribute(properties.SESSION_USER);

        modelAndView.addObject("user", user);

        modelAndView.setViewName("main");
        modelAndView.addObject(properties.CONTENT_CONTAINER, "user/logIn");
        modelAndView.addObject(properties.FRAGMENT_CONTAINER, "username");
        return modelAndView;
    }

    @PostMapping("/login/username")
    public ModelAndView postUsername(@ModelAttribute UserAuthentication user,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        session.setAttribute(properties.SESSION_USER, user);

        if (userService.findByUsername(user.getUsername()) == null) {
            redirectAttributes.addFlashAttribute("error", "El usuario no se encuentra en la base de datos");
            modelAndView.setViewName("redirect:/user/login/username");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/user/login/password");
        return modelAndView;
    }

    @GetMapping("/login/password")
    public ModelAndView logInPassword(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        UserAuthentication user = (UserAuthentication) session.getAttribute(properties.SESSION_USER);
        modelAndView.addObject("user", user);

        modelAndView.setViewName("main");
        modelAndView.addObject(properties.CONTENT_CONTAINER, "user/logIn");
        modelAndView.addObject(properties.FRAGMENT_CONTAINER, "password");
        return modelAndView;
    }

    @PostMapping("/login/password")
    public ModelAndView postPassword(@ModelAttribute UserAuthentication userForm,
                                     HttpServletResponse httpServletResponse,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        UserAuthentication userSession = (UserAuthentication) session.getAttribute(properties.SESSION_USER);
        userSession.setPassword(userForm.getPassword());

        if (userService.isCorrectUser(userSession)) {
            User user = userService.findByUsername(userSession.getUsername());

            if(userService.isLocked(user.getId())){
                redirectAttributes.addFlashAttribute("errorPwd", "Usuario bloqueado hasta " + user.getLockDate().toString() + " .");
                modelAndView.setViewName("redirect:/user/login/password");
                return modelAndView;
            }

            Cookie cookieLastUser = cookieManager.createCookie(properties.COOKIE_CONNECTED_USER, userSession.getUsername());
            httpServletResponse.addCookie(cookieLastUser);

            modelAndView.setViewName("redirect:/user/logged");
            return modelAndView;
        }

        int attemps = userSession.getAttemps();
        if (attemps > 1) {
            attemps--;
            userSession.setAttemps(attemps);

            redirectAttributes.addFlashAttribute("errorPwd", "Contraseña incorrecta, quedan " + userSession.getAttemps() + " intentos.");
        } else {
            LocalDate lockDate = userService.lockUserAuthentication(userSession.getUsername());
            redirectAttributes.addFlashAttribute("errorPwd", "Usuario bloqueado hasta " + lockDate.toString() + " .");
        }
        session.setAttribute(properties.SESSION_USER, userSession);

        modelAndView.setViewName("redirect:/user/login/password");
        return modelAndView;
    }

    @GetMapping("/logged")
    public ModelAndView logged(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        UserAuthentication user = (UserAuthentication) session.getAttribute(properties.SESSION_USER);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("main");
        modelAndView.addObject(properties.CONTENT_CONTAINER, "user/logged");

        return modelAndView;
    }

    @GetMapping("/logOut")
    public ModelAndView logOut(HttpSession session, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView();

        Cookie deletedCookie = cookieManager.deleteCookie(properties.COOKIE_CONNECTED_USER);
        httpServletResponse.addCookie(deletedCookie);

        UserAuthentication userAuthentication = (UserAuthentication) session.getAttribute(properties.SESSION_USER);
        Cookie lastUserCookie = cookieManager.createCookie(properties.COOKIE_LAST_USER, userAuthentication.getUsername());
        httpServletResponse.addCookie(lastUserCookie);

        session.invalidate();

        modelAndView.setViewName("redirect:/user/login");
        return modelAndView;
    }

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
