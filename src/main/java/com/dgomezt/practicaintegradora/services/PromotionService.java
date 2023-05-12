package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Promotion;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface PromotionService {
    List<Promotion> getAllPromotions();
    Promotion findPromotionById(Long id) throws ElementNotFoundException;

    void deleteEmptyPromotions();
}
