package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.entities.helpers.OrderState;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.OrderService;
import com.dgomezt.practicaintegradora.services.OrderStateService;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    ConfProperties confProperties;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderStateService orderStateService;

    @GetMapping("list")
    public ModelAndView orderListView(){
        ModelAndView modelAndView = new ModelAndView();

        List<Order> orders = orderService.getAll();
        modelAndView.addObject("orders", orders);

        modelAndView.setViewName("main");
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "order/list");
        return modelAndView;
    }

    @GetMapping("updateState/{id}")
    public ModelAndView updateStateOrder(@PathVariable Long id) throws ElementNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        Order order = orderService.findById(id);
        List<OrderState> possibleStates = orderStateService.getPossibleStatesForOrder(order);

        modelAndView.addObject("order", order);
        modelAndView.addObject("possibleStates", possibleStates);

        modelAndView.setViewName("main");
        modelAndView.addObject(confProperties.CONTENT_CONTAINER, "order/changeState");
        return modelAndView;
    }
}
