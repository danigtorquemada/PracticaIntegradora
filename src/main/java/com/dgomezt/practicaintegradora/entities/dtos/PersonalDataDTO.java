package com.dgomezt.practicaintegradora.entities.dtos;

import com.dgomezt.practicaintegradora.validations.CollectionContains;
import com.dgomezt.practicaintegradora.validations.EighteenPlusAge;
import com.dgomezt.practicaintegradora.validations.PatternDniNie;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@PatternDniNie(document = "document", documentType = "documentType")
public class PersonalDataDTO {
    @NotNull
    Integer interactions;
    @NotEmpty
    String firstName;
    @NotEmpty
    String lastName;
    @NotNull
    @CollectionContains(collection = CollectionContains.COLLECTIONS.GENDER)
    String gender;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @EighteenPlusAge
    LocalDate birthDate;
    @NotEmpty
    @CollectionContains(collection = CollectionContains.COLLECTIONS.COUNTRY)
    String country;
    @NotNull
    @CollectionContains(collection = CollectionContains.COLLECTIONS.TYPE_DOCUMENT)
    String documentType;
    @NotEmpty
    String document;

    public PersonalDataDTO() {
        interactions = 0;
    }
}
