package com.dgomezt.practicaintegradora.entities.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ClientQueryDTO {
    public String clientType = null;
    public LocalDate entryDateMax;
    public LocalDate entryDateMin;
    public String patternLastName = null;
    public BigDecimal totalSpentMoneyMax;
    public BigDecimal totalSpentMoneyMin;
}
