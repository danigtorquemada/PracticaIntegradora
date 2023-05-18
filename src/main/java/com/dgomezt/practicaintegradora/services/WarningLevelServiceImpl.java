package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.WarningLevel;
import com.dgomezt.practicaintegradora.repositories.WarningLevelRepository;
import com.dgomezt.practicaintegradora.repositories.WarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarningLevelServiceImpl implements WarningLevelService{
    @Autowired
    WarningLevelRepository warningLevelRepository;

    @Override
    public WarningLevel findById(Long id) {
        Optional<WarningLevel> optionalWarningLevel = warningLevelRepository.findById(id);
        return optionalWarningLevel.get();
    }
}
