package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Promotion;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

public interface PromotionService {
    Promotion findPromotionById(Long id) throws ElementNotFoundException;

    boolean deleteAll();
}
