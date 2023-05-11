package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.embeddables.*;
import com.dgomezt.practicaintegradora.entities.helpers.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
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
}

