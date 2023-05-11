package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Promotion;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionRepository promotionRepository;

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion findPromotionById(Long id) throws ElementNotFoundException {
        Optional<Promotion> promotionOptional =promotionRepository.findById(id);

        if(promotionOptional.isEmpty()) throw new ElementNotFoundException("Promotion not found");

        return promotionOptional.get();
    }

    @Override
    public boolean deleteAll() {
        promotionRepository.logicRemoveAllPromotions(LocalDate.now());

        return true;
    }
}
