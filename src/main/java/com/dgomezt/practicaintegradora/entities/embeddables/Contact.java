package com.dgomezt.practicaintegradora.entities.embeddables;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class Contact {
    public String firstName;
    public String lastName;
    public String phoneNumber;
}