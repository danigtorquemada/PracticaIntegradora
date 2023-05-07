package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.Promotion;
import com.dgomezt.practicaintegradora.entities.dtos.ClientDetailsDTO;
import com.dgomezt.practicaintegradora.entities.dtos.PromotionDetailsDTO;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    PromotionService promotionService;

    @GetMapping("/detail/{id}")
    public ModelAndView detailPromotion(@PathVariable String id) throws ElementNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        long promotionID = Long.parseLong(id);

        Promotion promotion = promotionService.findPromotionById(promotionID);
        PromotionDetailsDTO promotionDetailsDTO = PromotionDetailsDTO.fromPromotion(promotion);

        modelAndView.setViewName("main");
        modelAndView.addObject("promotionDetailsDTO", promotionDetailsDTO);
        modelAndView.addObject("content", "promotion/detail");
        return modelAndView;
    }
}
