package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Warning;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.WarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WarningServiceImpl implements WarningService{
    @Autowired
    WarningRepository warningRepository;

    @Override
    public void processWarning(Long id) throws ElementNotFoundException {
        Optional<Warning> optionalWarning = warningRepository.findById(id);

        if(optionalWarning.isEmpty()) throw new ElementNotFoundException("Warning with id " + id + " not found.");

        Warning warning = optionalWarning.get();
        warning.setProcessingDate(LocalDate.now());
    }
}
