package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.entities.Warning;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.UserAdminService;
import com.dgomezt.practicaintegradora.services.WarningService;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminLogInController {
    @Autowired
    UserAdminService userAdminService;
    @Autowired
    ConfProperties confProperties;
    @Autowired
    WarningService warningService;

    @GetMapping("/login")
    public ModelAndView adminForm(@ModelAttribute("userAdmin") UserAdmin userAdmin, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();

        UserAdmin userAdminSession = (UserAdmin) httpSession.getAttribute(confProperties.SESSION_ADMIN_USER);
        if (userAdminSession != null){
            modelAndView.setViewName("redirect:/admin/logged");
            return modelAndView;
        }

        List<UserAdmin> userAdmins = userAdminService.findAll();
        modelAndView.addObject("userAdmins", userAdmins);
        modelAndView.setViewName("main");
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "userAdmin/logIn");

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("userAdmin") UserAdmin userAdmin, HttpSession httpSession) throws ElementNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        Long id = userAdmin.getId();
        userAdmin = userAdminService.findById(id);

        httpSession.setAttribute(confProperties.SESSION_ADMIN_USER, userAdmin);

        modelAndView.setViewName("redirect:/admin/logged");
        return modelAndView;
    }

    @GetMapping("logged")
    public ModelAndView logged(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();

        List<Warning> warnings = warningService.findAllOrderByDate();

        UserAdmin userAdmin = (UserAdmin) httpSession.getAttribute(confProperties.SESSION_ADMIN_USER);

        modelAndView.addObject("userAdmin", userAdmin);
        modelAndView.addObject("warnings", warnings);
        modelAndView.setViewName("main");
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "userAdmin/logged");
        return modelAndView;
    }

    @GetMapping("/logOut")
    public ModelAndView logOut(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        session.invalidate();

        modelAndView.setViewName("redirect:/admin/login");
        return modelAndView;
    }
}
