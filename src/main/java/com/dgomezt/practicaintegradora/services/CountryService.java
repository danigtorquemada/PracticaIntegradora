package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.Country;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface CountryService {
    Country findById(Long id) throws ElementNotFoundException;
    List<Country> findAll();

    boolean isPresent(Long id);
}
