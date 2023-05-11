package com.dgomezt.practicaintegradora.entities.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClientQueryDTO {
    public List<String> clientTypes = null;
    public String patternLastName = null;
    public LocalDate entryDateMax;
    public LocalDate entryDateMin;
    public BigDecimal totalSpentMoneyMax;
    public BigDecimal totalSpentMoneyMin;

    public static ClientQueryDTO copy(ClientQueryDTO original) {
        ClientQueryDTO copy = new ClientQueryDTO();

        copy.clientTypes = original.clientTypes;
        copy.entryDateMax = original.entryDateMax;
        copy.entryDateMin = original.entryDateMin;
        copy.patternLastName = original.patternLastName;
        copy.totalSpentMoneyMax = original.totalSpentMoneyMax;
        copy.totalSpentMoneyMin = original.totalSpentMoneyMin;

        return copy;
    }
}
