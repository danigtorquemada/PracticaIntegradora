package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Warning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarningRepository extends JpaRepository<Warning, Long> {
    List<Warning> findByProcessingDateNullOrderByCreationDateAsc();
}