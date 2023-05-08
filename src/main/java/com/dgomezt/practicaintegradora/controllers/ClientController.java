package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.dtos.ClientDetailsDTO;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/client")
@Controller
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/detail/{id}")
    public ModelAndView detailClient(@PathVariable String id) throws ElementNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        long clientId = Long.parseLong(id);

        Client client = clientService.getClientById(clientId);
        ClientDetailsDTO clientDetailsDTO = ClientDetailsDTO.fromClient(client);

        modelAndView.setViewName("main");
        modelAndView.addObject("clientDetailsDTO", clientDetailsDTO);
        modelAndView.addObject("content", "client/detail");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView listClients(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("main");

        ClientDetailsDTO clientDetailsDTO = new ClientDetailsDTO();
        List<Client> clients = clientService.getAllClients();

        List<ClientDetailsDTO> clientDetailsDTOS = new ArrayList<>();
        for (Client client : clients) {
            clientDetailsDTOS.add(ClientDetailsDTO.fromClient(client));
        }

        modelAndView.addObject("clientDetailDTOS", clientDetailsDTOS);

        modelAndView.addObject("content", "client/list");
        return modelAndView;
    }
}
