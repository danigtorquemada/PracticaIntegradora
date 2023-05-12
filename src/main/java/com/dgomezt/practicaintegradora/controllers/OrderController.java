package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.services.OrderService;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    ConfProperties confProperties;
    @Autowired
    OrderService orderService;

    @GetMapping("list")
    public ModelAndView orderListView(){
        ModelAndView modelAndView = new ModelAndView();

        List<Order> orders = orderService.getAll();
        modelAndView.addObject("orders", orders);

        modelAndView.setViewName("main");
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "order/list");
        return modelAndView;
    }
}
