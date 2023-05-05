package com.dgomezt.practicaintegradora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ModelAndView handlingUniqueViolationException(RedirectAttributes redirectAttributes){
        ModelAndView mAV = new ModelAndView();
        redirectAttributes.addFlashAttribute("errorUnique", "Ya existe un usuario con ese email");
        mAV.setViewName("redirect:/user/signUp");
        return mAV;
    }
}
