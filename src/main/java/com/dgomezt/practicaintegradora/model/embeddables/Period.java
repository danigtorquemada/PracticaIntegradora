package com.dgomezt.practicaintegradora.model.embeddables;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Period {
    private Date initPeriod;
    private Date finalPeriod;
}