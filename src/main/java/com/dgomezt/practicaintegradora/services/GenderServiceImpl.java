package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.Gender;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderServiceImpl implements GenderService {
    @Autowired
    GenderRepository genderRepository;

    @Override
    public Gender findById(Long id) throws ElementNotFoundException {
        Optional<Gender> optionalGender = genderRepository.findById(id);
        if (optionalGender.isEmpty()) throw new ElementNotFoundException("Gender not found in database");
        return optionalGender.get();
    }

    @Override
    public List<Gender> findAll() {
        return genderRepository.findAll();
    }
    @Override
    public boolean isPresent(Long id) {
        return genderRepository.findById(id) != null;
    }
}
