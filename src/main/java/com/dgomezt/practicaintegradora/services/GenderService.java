package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.Gender;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface GenderService {
    Gender findById(Long id) throws ElementNotFoundException;
    List<Gender> findAll();
    boolean isPresent(Long id);
}
