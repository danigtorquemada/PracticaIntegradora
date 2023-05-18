package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.WarningLevel;

import java.util.Optional;

public interface WarningLevelService {
    WarningLevel findById(Long id);
}
