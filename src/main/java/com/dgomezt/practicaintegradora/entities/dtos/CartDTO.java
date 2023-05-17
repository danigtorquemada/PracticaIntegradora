package com.dgomezt.practicaintegradora.entities.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class CartDTO {
    Long id;
    LocalDate creationDate;
    BigDecimal price;
    Long client;
    List<ProductCartDTO> productCartDTOS;
}
