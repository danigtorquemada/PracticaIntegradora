package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.components.CookieManager;
import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.services.UserService;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/user")
@SessionAttributes("userAuthentication")
public class LogInController {
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

    @ModelAttribute("userAuthentication")
    public UserAuthentication sharedUserAuthentication() {
        return new UserAuthentication();
    }

    @GetMapping("/login")
    public ModelAndView logIn(@ModelAttribute("userAuthentication") UserAuthentication userAuthentication,
                              @CookieValue(value = "${custom.COOKIE_LAST_USER}", required = false) String lastUser,
                              @CookieValue(value = "${custom.COOKIE_CONNECTED_USER}", required = false) String connectedUser,
                              HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        if (connectedUser != null) {
            String username = cookieManager.readEncodedCookie(connectedUser);
            User user = userService.findByUsername(username);

            session.setAttribute(properties.SESSION_USER, user);

            modelAndView.setViewName("redirect:/user/logged");
            return modelAndView;
        }
        if (lastUser != null) {
            String username = cookieManager.readEncodedCookie(lastUser);
            userAuthentication.setUsername(username);

            modelAndView.setViewName("redirect:/user/login/password");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/user/login/username");
        return modelAndView;
    }

    @GetMapping("/login/username")
    public ModelAndView logInUsername(@ModelAttribute("userAuthentication") UserAuthentication userAuthentication) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("main");
        modelAndView.addObject(properties.CONTENT_CONTAINER, "user/logIn");
        modelAndView.addObject(properties.FRAGMENT_CONTAINER, "username");
        return modelAndView;
    }

    @PostMapping("/login/username")
    public ModelAndView postUsername(@ModelAttribute("userAuthentication") UserAuthentication userAuthentication,
                                     RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (userService.findByUsername(userAuthentication.getUsername()) == null) {
            redirectAttributes.addFlashAttribute("error", "El usuario no se encuentra en la base de datos");
            modelAndView.setViewName("redirect:/user/login/username");
        } else {
            modelAndView.setViewName("redirect:/user/login/password");
        }
        return modelAndView;
    }

    @GetMapping("/login/password")
    public ModelAndView logInPassword(@ModelAttribute("userAuthentication") UserAuthentication userAuthentication) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("main");
        modelAndView.addObject(properties.CONTENT_CONTAINER, "user/logIn");
        modelAndView.addObject(properties.FRAGMENT_CONTAINER, "password");
        return modelAndView;
    }

    @PostMapping("/login/password")
    public ModelAndView postPassword(@ModelAttribute("userAuthentication") UserAuthentication userAuthentication,
                                     HttpServletResponse httpServletResponse,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();


        if (userService.isCorrectUser(userAuthentication)) {
            User user = userService.findByUsername(userAuthentication.getUsername());

            if (userService.isLocked(user.getId())) {
                redirectAttributes.addFlashAttribute("errorPwd", "Usuario bloqueado hasta " + user.getLockDate().toString() + " .");
                modelAndView.setViewName("redirect:/user/login/password");
                return modelAndView;
            } else if (userService.isRemoved(user.getId())) {
                redirectAttributes.addFlashAttribute("errorPwd", "Usuario dado de baja.");
                modelAndView.setViewName("redirect:/user/login/password");
                return modelAndView;

            } else {
                session.setAttribute(properties.SESSION_USER, user);

                modelAndView.setViewName("redirect:/user/logged");
                return modelAndView;
            }
        } else
            modelAndView.setViewName("redirect:/user/login/password");

        int attemps = userAuthentication.getAttemps();
        if (attemps > 1) {
            attemps--;
            userAuthentication.setAttemps(attemps);

            redirectAttributes.addFlashAttribute("errorPwd", "Contraseña incorrecta, quedan " + userAuthentication.getAttemps() + " intentos.");
        } else {
            LocalDate lockDate = userService.lockUserAuthentication(userAuthentication.getUsername());
            redirectAttributes.addFlashAttribute("errorPwd", "Usuario bloqueado hasta " + lockDate.toString() + " .");
        }
        return modelAndView;
    }

    @GetMapping("/logged")
    public ModelAndView logged(HttpSession session, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) session.getAttribute(properties.SESSION_USER);

        Cookie cookieLastUser = cookieManager.createCookie(properties.COOKIE_CONNECTED_USER, user.getEmail());
        httpServletResponse.addCookie(cookieLastUser);

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

        User user = (User) session.getAttribute(properties.SESSION_USER);
        Cookie lastUserCookie = cookieManager.createCookie(properties.COOKIE_LAST_USER, user.getEmail());
        httpServletResponse.addCookie(lastUserCookie);

        session.invalidate();

        modelAndView.setViewName("redirect:/user/login");
        return modelAndView;
    }
}
