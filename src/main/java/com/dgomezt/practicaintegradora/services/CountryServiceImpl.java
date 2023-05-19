package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.Country;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService{
    @Autowired
    CountryRepository countryRepository;

    @Override
    public Country findById(Long id) throws ElementNotFoundException {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if(optionalCountry.isEmpty()) throw new ElementNotFoundException("Country not found in database");
        return optionalCountry.get();
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public boolean isPresent(Long id) {
        return countryRepository.findById(id) != null;
    }
}
