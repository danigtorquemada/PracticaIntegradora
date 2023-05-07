package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.dtos.ClientDetailsDTO;
import com.dgomezt.practicaintegradora.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/client")
@Controller
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/detail/{id}")
    public ModelAndView detailClient(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();

        long clientId = Long.parseLong(id);

        Client client = clientService.getClientById(clientId);
        ClientDetailsDTO clientDetailsDTO = ClientDetailsDTO.fromClient(client);

        modelAndView.setViewName("main");
        modelAndView.addObject("clientDetailsDTO", clientDetailsDTO);
        modelAndView.addObject("content", "client/detail");
        return modelAndView;
    }
}
