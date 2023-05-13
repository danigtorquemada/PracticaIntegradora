package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.embeddables.Auditory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user",
uniqueConstraints = @UniqueConstraint(name = "UK_user_email", columnNames = {"email"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_last_connection")
    private LocalDate dateLastConnection;

    @Column(name = "number_access")
    private Integer numberAccess;

    @Embedded
    private Auditory auditory;

    @Column(name = "lock_date")
    private LocalDate lockDate;

    public Auditory getAuditory() {
        if(auditory == null)
            auditory = new Auditory();

        return auditory;
    }

    public void addConnection(){
        if(numberAccess == null) numberAccess = 1;
        else numberAccess++;
    }
}