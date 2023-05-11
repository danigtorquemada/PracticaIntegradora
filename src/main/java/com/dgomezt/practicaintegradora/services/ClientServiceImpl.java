package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.dtos.ClientQueryDTO;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.ClientRepository;
import com.dgomezt.practicaintegradora.utilities.MysqlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    MysqlProperties mysqlProperties;

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
        ClientQueryDTO finalClientQueryDTO = ClientQueryDTO.copy(clientQueryDTO);

        if(clientQueryDTO.clientType == null || clientQueryDTO.clientType.isEmpty())
            finalClientQueryDTO.clientType = "%";

        if(clientQueryDTO.patternLastName == null)
            finalClientQueryDTO.patternLastName = "";

        if(clientQueryDTO.entryDateMax == null)
            finalClientQueryDTO.entryDateMax = mysqlProperties.getMaxDate();

        if(clientQueryDTO.entryDateMin == null)
            finalClientQueryDTO.entryDateMin = mysqlProperties.getMinDate();

        if(clientQueryDTO.totalSpentMoneyMin == null)
            finalClientQueryDTO.totalSpentMoneyMin = mysqlProperties.getMinDecimal();

        if(clientQueryDTO.totalSpentMoneyMax == null)
            finalClientQueryDTO.totalSpentMoneyMax = mysqlProperties.getMaxDecimal();

        return clientRepository.findByAuditory_EntryDateBetweenAndTotalSpentMoneyBetweenAndContact_LastNameContainsAndClientType_Type_AbbreviationIsLike(
                finalClientQueryDTO.entryDateMin, finalClientQueryDTO.entryDateMax,
                finalClientQueryDTO.totalSpentMoneyMin, finalClientQueryDTO.totalSpentMoneyMax,
                finalClientQueryDTO.patternLastName, finalClientQueryDTO.clientType
        );
    }
}
