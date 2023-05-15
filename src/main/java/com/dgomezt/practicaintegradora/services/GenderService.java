package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.Gender;

import java.util.List;

public interface GenderService {
    List<Gender> findAll();
    boolean isPresentByAbbreviation(String abbreviation);
}
