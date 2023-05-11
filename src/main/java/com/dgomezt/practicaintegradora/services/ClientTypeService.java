package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.ClientType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientTypeService {
    List<ClientType> getAll();
}
