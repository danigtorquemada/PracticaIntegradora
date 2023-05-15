package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.helpers.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    DocumentType findByType_Abbreviation(String abbreviation);
}