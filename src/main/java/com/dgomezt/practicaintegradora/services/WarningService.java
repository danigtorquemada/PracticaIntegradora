package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

public interface WarningService {
    void processWarning(Long id) throws ElementNotFoundException;
}
