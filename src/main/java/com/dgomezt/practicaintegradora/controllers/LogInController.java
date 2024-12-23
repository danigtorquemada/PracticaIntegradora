package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.components.CookieManager;
import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.services.ClientService;
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
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    ClientService clientService;
    @Autowired
    ConfProperties confProperties;

    @ModelAttribute("idSession")
    public String addIdSessionHTML(HttpSession session) {
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
            LocalDate lockDate = userService.lockUserByUsername(userAuthentication.getUsername());
            redirectAttributes.addFlashAttribute("errorPwd", "Usuario bloqueado hasta " + lockDate.toString() + " .");
        }
        return modelAndView;
    }

    @GetMapping("/logged")
    public ModelAndView logged(HttpSession session,
                               @CookieValue(value = "${custom.COOKIE_USERS_CONNECTIONS}", required = false) String usersCookie,
                               HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) session.getAttribute(properties.SESSION_USER);

        Map<String, Integer> usersConnections;
        if (usersCookie == null) {
            usersConnections = new HashMap<>();
            usersConnections.put(user.getEmail(), 1);
            cookieManager.saveCookieUsers(usersConnections);
        } else {
            usersConnections = cookieManager.getUsersFromCookieUser(usersCookie);
            if (usersConnections.containsKey(user.getEmail())) {
                usersConnections.put(user.getEmail(), usersConnections.get(user.getEmail()) + 1);
            } else
                usersConnections.put(user.getEmail(), 1);
        }
        Cookie cookieUsers = cookieManager.saveCookieUsers(usersConnections);
        httpServletResponse.addCookie(cookieUsers);

        Cookie cookieLastUser = cookieManager.createCookie(properties.COOKIE_CONNECTED_USER, user.getEmail());
        httpServletResponse.addCookie(cookieLastUser);

        user.addConnection();
        userService.save(user);


        if (!clientService.userHasClient(user)){
            modelAndView.setViewName("redirect:/client/register/step1");
            return modelAndView;
        }

        //modelAndView.addObject("user", user);
        //modelAndView.setViewName("main");
        //modelAndView.addObject(properties.CONTENT_CONTAINER, "user/logged");

        modelAndView.setViewName("redirect:http://localhost:5173/");

        return modelAndView;
    }

    @GetMapping("/logOut")
    public ModelAndView logOut(String username, HttpSession session, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView();

        Cookie deletedCookie = cookieManager.deleteCookie(properties.COOKIE_CONNECTED_USER);
        httpServletResponse.addCookie(deletedCookie);

        User user = (User) session.getAttribute(properties.SESSION_USER);

        if (user == null) {
            user = userService.findByUsername(username);
            session.setAttribute(confProperties.SESSION_USER, user);
        }

        Cookie lastUserCookie = cookieManager.createCookie(properties.COOKIE_LAST_USER, user.getEmail());
        httpServletResponse.addCookie(lastUserCookie);

        session.invalidate();

        modelAndView.setViewName("redirect:http://localhost:5173/");
        return modelAndView;
    }
}
