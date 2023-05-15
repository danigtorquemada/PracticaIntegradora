package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.TypeRoad;

import java.util.List;

public interface TypeRoadService {
    List<TypeRoad> findAll();
    boolean isPresent(Long id);
}
