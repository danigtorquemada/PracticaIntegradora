package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.entities.Warning;
import com.dgomezt.practicaintegradora.repositories.WarningRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WarningServiceImpl implements WarningService{
    @Autowired
    WarningRepository warningRepository;

    @Override
    public List<Warning> findAllOrderByDate() {
        return warningRepository.findByProcessingDateNullOrderByCreationDateAsc();
    }

    @Override
    public void processWarning(Long id, UserAdmin userAdmin){
        Optional<Warning> optionalWarning = warningRepository.findById(id);

        if(optionalWarning.isEmpty()) return;

        Warning warning = optionalWarning.get();
        warning.setProcessingDate(LocalDate.now());
        warning.setUser(userAdmin);
        warningRepository.save(warning);
    }

    @Override
    public Warning save(Warning newWarning) {
        return warningRepository.save(newWarning);
    }
}
