package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.ClientType;
import com.dgomezt.practicaintegradora.repositories.ClientTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientTypeServiceImpl implements ClientTypeService{
    @Autowired
    ClientTypeRepository clientTypeRepository;

    @Override
    public List<ClientType> getAll() {
        return clientTypeRepository.findAll();
    }
}
