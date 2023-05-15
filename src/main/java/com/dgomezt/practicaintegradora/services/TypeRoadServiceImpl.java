package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.TypeRoad;
import com.dgomezt.practicaintegradora.repositories.TypeRoadRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeRoadServiceImpl implements TypeRoadService{
    @Autowired
    TypeRoadRepository typeRoadRepository;
    @Override
    public List<TypeRoad> findAll() {
        return typeRoadRepository.findAll();
    }

    @Override
    public boolean isPresent(Long id) {
        return typeRoadRepository.findById(id) != null;
    }
}
