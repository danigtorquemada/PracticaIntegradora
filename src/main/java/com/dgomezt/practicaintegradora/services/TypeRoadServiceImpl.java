package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.helpers.TypeRoad;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.TypeRoadRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public TypeRoad findById(Long id) throws ElementNotFoundException {
        Optional<TypeRoad> optionalTypeRoad = typeRoadRepository.findById(id);
        if (optionalTypeRoad.isEmpty()) throw new ElementNotFoundException("Type road not found in Database");

        return typeRoadRepository.findById(id).get();
    }
}
