package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotion")
public class PromotionRestController {
    @Autowired
    PromotionService promotionService;

    @GetMapping("/deleteAll")
    public boolean deleteAll(){
        promotionService.deleteAll();
        return true;
    }
}
