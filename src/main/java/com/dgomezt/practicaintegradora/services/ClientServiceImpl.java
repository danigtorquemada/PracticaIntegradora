package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.entities.dtos.ClientQueryDTO;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.ContactDataDTO;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.OtherDataDTO;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.PersonalDataDTO;
import com.dgomezt.practicaintegradora.entities.helpers.*;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.ClientRepository;
import com.dgomezt.practicaintegradora.utilities.MysqlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    MysqlProperties mysqlProperties;
    @Autowired
    ClientTypeService clientTypeService;
    @Autowired
    GenderService genderService;
    @Autowired
    CountryService countryService;
    @Autowired
    DocumentTypeService documentTypeService;
    @Autowired
    TypeRoadService typeRoadService;
    @Autowired
    AddressService addressService;

    // @Autowired
    // AddressService addressService;
    // @Autowired
    // GenderService genderService;
    // @Autowired
    // CountryService countryService;
    // @Autowired
    // DocumentTypeService documentTypeService;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(long id) throws ElementNotFoundException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) throw new ElementNotFoundException("Client not found");
        return optionalClient.get();
    }

    @Override
    public List<Client> getParameterizedQueryClients(ClientQueryDTO clientQueryDTO) {
        ClientQueryDTO finalClientQueryDTO = ClientQueryDTO.copy(clientQueryDTO);

        if (clientQueryDTO.clientTypes == null || clientQueryDTO.clientTypes.isEmpty()) {
            finalClientQueryDTO.clientTypes = clientTypeService.getAllAbbreviations();
        }

        if (clientQueryDTO.patternLastName == null)
            finalClientQueryDTO.patternLastName = "";

        if (clientQueryDTO.entryDateMax == null)
            finalClientQueryDTO.entryDateMax = mysqlProperties.getMaxDate();

        if (clientQueryDTO.entryDateMin == null)
            finalClientQueryDTO.entryDateMin = mysqlProperties.getMinDate();

        if (clientQueryDTO.totalSpentMoneyMin == null)
            finalClientQueryDTO.totalSpentMoneyMin = mysqlProperties.getMinDecimal();

        if (clientQueryDTO.totalSpentMoneyMax == null)
            finalClientQueryDTO.totalSpentMoneyMax = mysqlProperties.getMaxDecimal();

        return clientRepository.findByAuditory_EntryDateBetweenAndTotalSpentMoneyBetweenAndContact_LastNameContainsAndClientType_Type_AbbreviationIn(
                finalClientQueryDTO.entryDateMin, finalClientQueryDTO.entryDateMax,
                finalClientQueryDTO.totalSpentMoneyMin, finalClientQueryDTO.totalSpentMoneyMax,
                finalClientQueryDTO.patternLastName, finalClientQueryDTO.clientTypes
        );
    }

    @Override
    public Client registerClient(Client newClient) {
        return clientRepository.save(newClient);
    }

    @Override
    public boolean userHasClient(User user) {
        return clientRepository.findByUser(user) != null;
    }

    @Override
    public Client registerDTOSRegisterStep(PersonalDataDTO personalDataDTO, ContactDataDTO contactDataDTO, OtherDataDTO otherDataDTO, User user) throws ElementNotFoundException {
        Client newClient = new Client();

        newClient.setClientType(null);
        newClient.setUser(user);

        //PersonalData DTO
        newClient.getContact().setFirstName(personalDataDTO.getFirstName());
        newClient.getContact().setLastName(personalDataDTO.getLastName());
        Gender gender = genderService.findById(personalDataDTO.getGender());
        newClient.setGender(gender);
        newClient.setBirthDate(personalDataDTO.getBirthDate());
        Country country = countryService.findById(personalDataDTO.getCountry());
        newClient.setCountry(country);
        DocumentType documentType = documentTypeService.findById(personalDataDTO.getDocumentType());
        newClient.setDocumentType(documentType);
        newClient.setDocument(personalDataDTO.getDocument());

        //ContactData DTO
        newClient.getContact().setPhoneNumber(contactDataDTO.getPhoneNumber());
        TypeRoad typeRoad = typeRoadService.findById(contactDataDTO.getTypeRoad());
        Address address = new Address();
        address.setTypeRoad(typeRoad);
        address.setName(contactDataDTO.getName());
        address.setNumber(Integer.valueOf(contactDataDTO.getNumber()));
        address.setPortal(contactDataDTO.getPortal());
        address.setFloor(contactDataDTO.getFloor());
        address.setCity(contactDataDTO.getCity());
        address.setState(contactDataDTO.getState());
        address.setPostcode(contactDataDTO.getPostcode());

        Address savedAddress = addressService.save(address);

        newClient.setAddress(savedAddress);

        //OtherData DTO
        Set<Category> categories = new HashSet<>();
        for (Long interestedCategory : otherDataDTO.getInterestedCategories()) {
            Category newCategory = new Category();
            newCategory.setId(interestedCategory);
            categories.add(newCategory);
        }

        newClient.setInterestedCategories(categories);
        newClient.setComments(otherDataDTO.getComments());
        newClient.setLicense(otherDataDTO.getLicense().equalsIgnoreCase("on"));

        newClient.setUser(user);

        return registerClient(newClient);
    }
}
