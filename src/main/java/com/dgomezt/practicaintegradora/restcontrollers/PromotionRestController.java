package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotion")
public class PromotionRestController {
    @Autowired
    PromotionService promotionService;

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        promotionService.deleteEmptyPromotions();
    }
}
