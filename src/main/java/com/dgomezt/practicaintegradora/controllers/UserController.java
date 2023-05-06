package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.components.CookieManager;
import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.services.UserService;
import com.dgomezt.practicaintegradora.utilities.UserAuthentication;
import com.dgomezt.practicaintegradora.utilities.UserForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.catalina.filters.ExpiresFilter;
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
    private static final String SESSION_USER = "userSession";

    @Autowired
    CookieManager cookieManager;
    @Autowired
    UserService userService;

    @GetMapping("/signUp")
    public ModelAndView printForm() {
        ModelAndView modelAndView = new ModelAndView();

        UserForm userForm = new UserForm();

        modelAndView.addObject(userForm);
        modelAndView.setViewName("main");
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
                              HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();


        if (connectedUser != null) {
            String username = cookieManager.readEncodedCookie(connectedUser);
            user.setUsername(username);
            session.setAttribute(SESSION_USER, user);

            modelAndView.setViewName("redirect:/user/logged");
            return modelAndView;
        }
        if(lastUser != null){
            String username = cookieManager.readEncodedCookie(lastUser);
            user.setUsername(username);
            session.setAttribute(SESSION_USER, user);

            modelAndView.setViewName("redirect:/user/login/password");
            return modelAndView;
        }


        modelAndView.addObject("user", user);

        modelAndView.setViewName("main");
        modelAndView.addObject(CONTENT_CONTAINER, "user/logIn");
        modelAndView.addObject(FRAGMENT_CONTAINER, "username");
        return modelAndView;
    }

    @GetMapping("/login/username")
    public ModelAndView logInUsername(@ModelAttribute UserAuthentication user, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        if (session.getAttribute("user") != null)
            user = (UserAuthentication) session.getAttribute(SESSION_USER);
        if (session.getAttribute("error") != null)
            modelAndView.addObject("error", session.getAttribute("error"));

        modelAndView.addObject("user", user);

        modelAndView.setViewName("main");
        modelAndView.addObject(CONTENT_CONTAINER, "user/logIn");
        modelAndView.addObject(FRAGMENT_CONTAINER, "username");
        return modelAndView;
    }

    @PostMapping("/login/username")
    public ModelAndView postUsername(@ModelAttribute UserAuthentication user,
                                     HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        session.setAttribute(SESSION_USER, user);

        if (userService.findByUsername(user.getUsername()) == null) {
            session.setAttribute("error", "El usuario no se encuentra en la base de datos");
            modelAndView.setViewName("redirect:/user/login/username");
            return modelAndView;
        }

        session.removeAttribute("error");

        modelAndView.setViewName("redirect:/user/login/password");
        return modelAndView;
    }

    @GetMapping("/login/password")
    public ModelAndView logInPassword(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        UserAuthentication user = (UserAuthentication) session.getAttribute(SESSION_USER);
        modelAndView.addObject("user", user);

        modelAndView.addObject("errorPwd", session.getAttribute("errorPwd"));

        modelAndView.setViewName("main");
        modelAndView.addObject(CONTENT_CONTAINER, "user/logIn");
        modelAndView.addObject(FRAGMENT_CONTAINER, "password");
        return modelAndView;
    }

    @PostMapping("/login/password")
    public ModelAndView postPassword(HttpServletResponse httpServletResponse,
                                     HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        UserAuthentication userSession = (UserAuthentication) session.getAttribute(SESSION_USER);

        if (userService.isCorrectUser(userSession)) {
            session.removeAttribute("errorPwd");

            Cookie cookieLastUser = cookieManager.createCookie(COOKIE_CONNECTED_USER, userSession.getUsername());
            httpServletResponse.addCookie(cookieLastUser);

            modelAndView.setViewName("redirect:/user/logged");
            return modelAndView;
        }


        int attemps = userSession.getAttemps();
        if(attemps > 0){
            attemps--;
            userSession.setAttemps(attemps);
            session.setAttribute("errorPwd", "Contraseña incorrecta, quedan " + userSession.getAttemps() + " intentos.");
        }else {
            userService.blockUser(userSession);
            session.setAttribute("errorPwd", "Usuario bloqueado.");
        }
        session.setAttribute(SESSION_USER, userSession);

        modelAndView.setViewName("redirect:/user/login/password");
        return modelAndView;
    }

    @GetMapping("/logged")
    public ModelAndView logged(HttpSession session, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();

        UserAuthentication user = (UserAuthentication) session.getAttribute(SESSION_USER);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("main");
        modelAndView.addObject(CONTENT_CONTAINER, "user/logged");

        return modelAndView;
    }

    @GetMapping("/logOut")
    public ModelAndView logOut(HttpSession session, HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView();

        Cookie deletedCookie = cookieManager.deleteCookie(COOKIE_CONNECTED_USER);
        httpServletResponse.addCookie(deletedCookie);

        UserAuthentication userAuthentication = (UserAuthentication) session.getAttribute(SESSION_USER);
        Cookie lastUserCookie = cookieManager.createCookie(COOKIE_LAST_USER, userAuthentication.getUsername());
        httpServletResponse.addCookie(lastUserCookie);

        session.invalidate();
        modelAndView.setViewName("redirect:/user/login");
        return modelAndView;
    }

    @GetMapping("/password_user")
    public @ResponseBody String recoverPassword(String user) {
        return userService.findByUsername(user).getPassword();
    }
}
