package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ElementNotFoundException.class)
    public ModelAndView noSuchElement(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("main");
        modelAndView.addObject("content", "error/error");
        modelAndView.addObject("fragment", "notFound");
        return modelAndView;
    }
}
