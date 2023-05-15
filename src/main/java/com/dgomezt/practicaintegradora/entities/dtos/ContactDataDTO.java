package com.dgomezt.practicaintegradora.entities.dtos;

import com.dgomezt.practicaintegradora.entities.helpers.Address;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ContactDataDTO {
    @NotNull
    Integer interactions;
    @NotEmpty
    @Pattern(regexp = "^[0-9]{9}$")
    String phoneNumber;
    Address address;
}
