package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.Gender;
import com.dgomezt.practicaintegradora.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {
    @Autowired
    GenderRepository genderRepository;
    @Override
    public List<Gender> findAll() {
        return genderRepository.findAll();
    }
    @Override
    public boolean isPresentByAbbreviation(String abbreviation) {
        return genderRepository.findByType_Abbreviation(abbreviation) != null;
    }
}