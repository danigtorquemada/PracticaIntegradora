package com.dgomezt.practicaintegradora.entities.dtos.clientForm;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;

@Data
public class OtherDataDTO {
    @NotNull
    Integer interactions;
    ArrayList<Long> interestedCategories;
    @NotNull
    String comments;
    @NotNull
    @Pattern(regexp = "^on$", message = "{error.license}")
    String license;

    public OtherDataDTO() {
        interactions = 0;
    }
}
