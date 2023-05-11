package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.dtos.ClientQueryDTO;
import com.dgomezt.practicaintegradora.entities.helpers.ClientType;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.ClientService;
import com.dgomezt.practicaintegradora.services.ClientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/client")
@Controller
public class ClientController {

    @Autowired
    ClientService clientService;
    @Autowired
    ClientTypeService clientTypeService;

    @GetMapping("/detail/{id}")
    public ModelAndView detailClient(@PathVariable String id) throws ElementNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        long clientId = Long.parseLong(id);

        Client client = clientService.getClientById(clientId);

        modelAndView.setViewName("main");
        modelAndView.addObject("client", client);
        modelAndView.addObject("content", "client/detail");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView listClients(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("main");

        List<Client> clients = clientService.getAllClients();

        modelAndView.addObject("clients", clients);

        modelAndView.addObject("content", "client/list");
        return modelAndView;
    }


    @GetMapping("/query")
    public ModelAndView queryUsers(ClientQueryDTO clientQueryDTO){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("main");

        List<Client> clients = clientService.getParameterizedQueryClients(clientQueryDTO);
        List<ClientType> clientTypes = clientTypeService.getAll();

        modelAndView.addObject("clientQueryDTO", clientQueryDTO);

        modelAndView.addObject("clients", clients);
        modelAndView.addObject("clientTypes", clientTypes);
        modelAndView.addObject("content", "client/query");
        return modelAndView;
    }
}
