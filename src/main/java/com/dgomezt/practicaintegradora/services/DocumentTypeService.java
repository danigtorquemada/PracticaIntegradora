package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.DocumentType;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface DocumentTypeService {
    DocumentType findById(Long id) throws ElementNotFoundException;
    List<DocumentType> findAll();
    boolean isPresent(Long id);
}
