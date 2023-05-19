package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.entities.Warning;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface WarningService {
    List<Warning> findAllOrderByDate();
    void processWarning(Long id, UserAdmin userAdmin);

    Warning save(Warning newWarning);
}
