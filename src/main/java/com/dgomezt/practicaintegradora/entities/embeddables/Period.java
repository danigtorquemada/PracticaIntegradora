package com.dgomezt.practicaintegradora.entities.embeddables;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Period {
    private LocalDate initPeriod;
    private LocalDate finalPeriod;
}