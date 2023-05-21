package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.entities.dtos.ClientQueryDTO;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.ContactDataDTO;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.OtherDataDTO;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.PersonalDataDTO;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(long id) throws ElementNotFoundException;

    List<Client> getParameterizedQueryClients(ClientQueryDTO clientQueryDTO);

    Client registerClient(Client newClient);

    boolean userHasClient(User user);

    Client registerDTOSRegisterStep(PersonalDataDTO personalDataDTO, ContactDataDTO contactDataDTO, OtherDataDTO otherDataDTO, User user) throws ElementNotFoundException;

    Client getByUserId(Long userId);
}
