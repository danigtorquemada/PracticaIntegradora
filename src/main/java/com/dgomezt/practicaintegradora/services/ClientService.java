package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.dtos.ClientQueryDTO;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(long id) throws ElementNotFoundException;

    List<Client> getParameterizedQueryClients(ClientQueryDTO clientQueryDTO);

    Client registerClient(Client newClient);
}
