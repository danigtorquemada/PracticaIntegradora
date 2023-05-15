package com.dgomezt.practicaintegradora.entities.dtos.clientForm;

import com.dgomezt.practicaintegradora.entities.helpers.Address;
import com.dgomezt.practicaintegradora.validations.CollectionContains;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ContactDataDTO {
    @NotNull
    Integer interactions;
    @NotEmpty
    @Pattern(regexp = "^[0-9]{9}$")
    String phoneNumber;
    @NotNull
    @CollectionContains(collection = CollectionContains.COLLECTIONS.TYPE_ROAD)
    Long typeRoad;
    @NotEmpty
    String name;
    @NotEmpty
    @Pattern(regexp = "^[0-9]*$", message = "{error.number}")
    String number;
    String portal;
    @Pattern(regexp = "^[0-9]*$", message = "{error.number}")
    String floor;
    String door;
    @NotEmpty
    String city;
    String state;
    @NotEmpty
    String postcode;

    public ContactDataDTO(){
        interactions = 0;
    }
}
