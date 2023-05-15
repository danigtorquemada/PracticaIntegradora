package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.Country;

import java.util.List;

public interface CountryService {
    Country findById(Long id);
    List<Country> findAll();

    boolean isPresent(Long id);
}
