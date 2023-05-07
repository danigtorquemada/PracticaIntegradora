package com.dgomezt.practicaintegradora.entities.dtos;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.embeddables.Auditory;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientDetailsDTO {
    Long id;
    Long user;
    String gender;
    String birthDate;
    String country;
    String documentType;
    String document;
    String phoneNumber;
    String firstName;
    String lastName;
    String address;
    String deliveryAddress;
    BigDecimal totalSpentMoney;
    String clientType;
    String interestedCategories;
    String comments;
    Boolean license;
    String entryDate;
    Long entryUser;
    String lastModificationDate;
    Long lastModificationUser;
    String removedDate;
    Long removedUser;

    public static ClientDetailsDTO fromClient(Client client) {
        ClientDetailsDTO clientDetailsDTO = new ClientDetailsDTO();

        clientDetailsDTO.id = client.getId();

        if (client.getUser() != null)
            clientDetailsDTO.user = client.getUser().getId();

        clientDetailsDTO.gender = client.getGender().getType().getType();
        clientDetailsDTO.birthDate = client.getBirthDate().toString();
        clientDetailsDTO.country = client.getCountry().getType().getType();
        clientDetailsDTO.documentType = client.getDocumentType().getType().getType();
        clientDetailsDTO.document = client.getDocument();
        clientDetailsDTO.phoneNumber = client.getContact().getPhoneNumber();
        clientDetailsDTO.firstName = client.getContact().getFirstName();
        clientDetailsDTO.lastName = client.getContact().getLastName();
        clientDetailsDTO.address = client.getAddress().toString();
        clientDetailsDTO.totalSpentMoney = client.getTotalSpentMoney();
        clientDetailsDTO.clientType = client.getClientType().getType().getType();
        clientDetailsDTO.comments = client.getComments();
        clientDetailsDTO.license = client.getLicense();

        if (client.getAuditory() == null) client.setAuditory(new Auditory());

        clientDetailsDTO.entryDate = client.getAuditory().getEntryDate().toString();

        if (client.getAuditory().getEntryUser() != null)
            clientDetailsDTO.entryUser = client.getAuditory().getEntryUser().getId();

        clientDetailsDTO.lastModificationDate = client.getAuditory().getLastModificationDate().toString();

        if (client.getAuditory().getLastModificationUser() != null)
            clientDetailsDTO.lastModificationUser = client.getAuditory().getLastModificationUser().getId();

        clientDetailsDTO.removedDate = client.getAuditory().getRemovedDate().toString();

        if(client.getAuditory().getRemovedUser() != null)
        clientDetailsDTO.removedUser = client.getAuditory().getRemovedUser().getId();

        return clientDetailsDTO;
    }
}