package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    Client getClientById(long id);
}