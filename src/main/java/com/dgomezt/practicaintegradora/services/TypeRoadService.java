package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.TypeRoad;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface TypeRoadService {
    List<TypeRoad> findAll();
    boolean isPresent(Long id);

    TypeRoad findById(Long id) throws ElementNotFoundException;
}
