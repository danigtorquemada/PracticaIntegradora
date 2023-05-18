package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Warning;

import java.util.List;

public interface WarningService {
    List<Warning> findAll();
    void processWarning(Long id);

    Warning save(Warning newWarning);
}
