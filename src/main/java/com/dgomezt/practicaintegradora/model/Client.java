package com.dgomezt.practicaintegradora.model;

import com.dgomezt.practicaintegradora.model.embeddables.Address;
import com.dgomezt.practicaintegradora.model.embeddables.Contact;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    private Address address;

    @Embedded
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_Client_Category"))
    private Category category;

    @OneToOne(mappedBy = "client", orphanRemoval = true)
    private User user;
}