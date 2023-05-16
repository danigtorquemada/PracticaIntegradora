package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.dtos.clientForm.ContactDataDTO;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.OtherDataDTO;
import com.dgomezt.practicaintegradora.entities.dtos.clientForm.PersonalDataDTO;
import com.dgomezt.practicaintegradora.entities.embeddables.*;
import com.dgomezt.practicaintegradora.entities.helpers.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.print.attribute.standard.MediaSize;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "FK_client_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "gender_id",
            foreignKey = @ForeignKey(name = "FK_client_gender"))
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "country_id",
            foreignKey = @ForeignKey(name = "FK_client_country"))
    private Country country;

    @ManyToOne
    @JoinColumn(name = "document_type_id",
            foreignKey = @ForeignKey(name = "FK_client_typeDocument"))
    private DocumentType documentType;

    @Column(name = "document")
    private String document;

    @Embedded
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "address_id",
            foreignKey = @ForeignKey(name = "FK_client_address"))
    private Address address;

    @ManyToMany
    @JoinTable(name = "client_delivery_addresses",
            joinColumns = @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "FK_clientDeliveryAddresses_client")),
            inverseJoinColumns = @JoinColumn(name = "addresses_id", foreignKey = @ForeignKey(name = "FK_clientDeliveryAddresses_address")))
    private Set<Address> deliveryAddress = new LinkedHashSet<>();

    @Column(name = "total_spent_money", precision = 19, scale = 2)
    private BigDecimal totalSpentMoney;

    @ManyToOne
    @JoinColumn(name = "client_type_id",
            foreignKey = @ForeignKey(name = "FK_Client_ClientType"))
    private ClientType clientType;

    @ManyToMany
    @JoinTable(name = "client_categories",
            joinColumns = @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "FK_clientCategories_client")),
            inverseJoinColumns = @JoinColumn(name = "categories_id", foreignKey = @ForeignKey(name = "FK_clientCategories_category")))
    private Set<Category> interestedCategories = new LinkedHashSet<>();

    @Column(name = "comments")
    private String comments;

    @Column(name = "license")
    private Boolean license;

    @Embedded
    private Auditory auditory;

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    private Set<Order> orders = new LinkedHashSet<>();

    public Auditory getAuditory() {
        if (auditory == null)
            auditory = new Auditory();

        return auditory;
    }
    public Client(){
        auditory = new Auditory();
        contact = new Contact();
        country = new Country();
        documentType = new DocumentType();
        address = new Address();
        gender = new Gender();
        clientType = new ClientType();
        user = new User();
    }

    public static Client fromDTOS(PersonalDataDTO personalDataDTO, ContactDataDTO contactDataDTO, OtherDataDTO otherDataDTO){
        Client newClient = new Client();
        newClient.clientType = null;
        newClient.user = null;
        //PERSONAL DTO
        newClient.contact.firstName = personalDataDTO.getFirstName();
        newClient.contact.lastName = personalDataDTO.getLastName();
        newClient.gender.setId(personalDataDTO.getGender());
        newClient.birthDate = personalDataDTO.getBirthDate();
        newClient.country.setId(personalDataDTO.getCountry());
        newClient.documentType.setId(personalDataDTO.getDocumentType());
        newClient.document = personalDataDTO.getDocument();

        //CONTACT DTO
        newClient.contact.phoneNumber = contactDataDTO.getPhoneNumber();
        TypeRoad typeRoad = new TypeRoad(contactDataDTO.getTypeRoad(), null);
        newClient.address.setTypeRoad(typeRoad);
        newClient.address.setName(contactDataDTO.getName());
        newClient.address.setNumber(Integer.valueOf(contactDataDTO.getNumber()));
        newClient.address.setPortal(contactDataDTO.getPortal());
        newClient.address.setFloor(contactDataDTO.getFloor());
        newClient.address.setCity(contactDataDTO.getCity());
        newClient.address.setState(contactDataDTO.getState());
        newClient.address.setPostcode(contactDataDTO.getPostcode());

        //OTHER DTO
        Set<Category> categories = new HashSet<>();
        for (Long interestedCategory : otherDataDTO.getInterestedCategories()) {
            Category newCategory = new Category();
            newCategory.setId(interestedCategory);
            categories.add(newCategory);
        }

        newClient.interestedCategories = categories;
        newClient.comments = otherDataDTO.getComments();
        newClient.license = otherDataDTO.getLicense().equalsIgnoreCase("on");

        return newClient;
    }
}

