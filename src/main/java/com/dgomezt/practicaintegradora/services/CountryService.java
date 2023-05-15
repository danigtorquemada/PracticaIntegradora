package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();
    boolean isPresentByAbbreviation(String abbreviation);
}
