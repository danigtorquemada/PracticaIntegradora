package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.DocumentType;
import com.dgomezt.practicaintegradora.repositories.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService{
    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    @Override
    public boolean isPresentByAbbreviation(String abbreviation) {
        return documentTypeRepository.findByType_Abbreviation(abbreviation) != null;
    }
}
