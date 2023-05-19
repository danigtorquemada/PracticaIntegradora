package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.DocumentType;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService{
    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Override
    public DocumentType findById(Long id) throws ElementNotFoundException {
        Optional<DocumentType> optionalDocumentType = documentTypeRepository.findById(id);
        if (optionalDocumentType.isEmpty()) throw new ElementNotFoundException("DocumentType not found in Database");
        return optionalDocumentType.get();
    }

    @Override
    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    @Override
    public boolean isPresent(Long id) {
        return documentTypeRepository.findById(id) != null;
    }
}
