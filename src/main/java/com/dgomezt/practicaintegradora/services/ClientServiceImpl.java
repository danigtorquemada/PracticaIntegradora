package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.dtos.ClientQueryDTO;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(long id) throws ElementNotFoundException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isEmpty())  throw new ElementNotFoundException("Client not found");
        return optionalClient.get();
    }

    @Override
    public List<Client> getParameterizedQueryClients(ClientQueryDTO clientQueryDTO){
        return clientRepository.findByAuditory_EntryDateBetweenAndTotalSpentMoneyBetweenAndContact_LastNameContainsAndClientType_Type_Abbreviation(
            clientQueryDTO.entryDateMin, clientQueryDTO.entryDateMax,
                clientQueryDTO.totalSpentMoneyMin, clientQueryDTO.totalSpentMoneyMax,
                clientQueryDTO.patternLastName, clientQueryDTO.clientType
        );
    }
}
