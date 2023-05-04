package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.helpers.Auditory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_last_connection")
    private LocalDate dateLastConnection;

    @Column(name = "number_access")
    private Integer numberAccess;

    @OneToOne
    @JoinColumn(name = "auditory_id",
            foreignKey = @ForeignKey(name = "FK_user_auditory"))
    private Auditory auditory;

    @Column(name = "block_date")
    private LocalDate blockDate;

    /******* RELATIONSHIPS **********/

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Order> orders = new LinkedHashSet<>();
}