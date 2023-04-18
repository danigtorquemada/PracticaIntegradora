package com.dgomezt.practicaintegradora.entities.embeddables;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}