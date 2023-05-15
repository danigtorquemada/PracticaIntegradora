package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.Gender;

import java.util.List;

public interface GenderService {
    Gender findById(Long id);
    List<Gender> findAll();
    boolean isPresent(Long id);
}
