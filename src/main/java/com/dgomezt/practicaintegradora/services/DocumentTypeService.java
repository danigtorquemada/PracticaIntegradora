package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.DocumentType;

import java.util.List;

public interface DocumentTypeService {
    DocumentType findById(Long id);
    List<DocumentType> findAll();
    boolean isPresent(Long id);
}
