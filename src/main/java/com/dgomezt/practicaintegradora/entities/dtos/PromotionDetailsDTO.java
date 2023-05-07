package com.dgomezt.practicaintegradora.entities.dtos;

import com.dgomezt.practicaintegradora.entities.Promotion;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PromotionDetailsDTO {
    Long id;
    String description;
    LocalDate initPeriod;
    LocalDate finalPeriod;
    BigDecimal discount;
    LocalDate entryDate;
    Long entryUser;
    LocalDate lastModificationDate;
    Long lastModificationUser;
    LocalDate removedDate;
    Long removedUser;
    String products;

    public static PromotionDetailsDTO fromPromotion(Promotion promotion) {
        PromotionDetailsDTO promotionDetailsDTO = new PromotionDetailsDTO();

        promotionDetailsDTO.id = promotion.getId();
        promotionDetailsDTO.description = promotion.getDescription();
        promotionDetailsDTO.initPeriod = promotion.getPeriod().getInitPeriod();
        promotionDetailsDTO.finalPeriod = promotion.getPeriod().getFinalPeriod();
        promotionDetailsDTO.discount = promotion.getDiscount();
        if (promotion.getAuditory() != null) {
            promotionDetailsDTO.entryDate = promotion.getAuditory().getEntryDate();
            promotionDetailsDTO.lastModificationDate = promotion.getAuditory().getLastModificationDate();
            promotionDetailsDTO.removedDate = promotion.getAuditory().getRemovedDate();

            if (promotion.getAuditory().getEntryUser() != null)
                promotionDetailsDTO.entryUser = promotion.getAuditory().getEntryUser().getId();
            if (promotion.getAuditory().getLastModificationUser() != null)
                promotionDetailsDTO.lastModificationUser = promotion.getAuditory().getLastModificationUser().getId();
            if (promotion.getAuditory().getRemovedUser() != null)
                promotionDetailsDTO.removedUser = promotion.getAuditory().getRemovedUser().getId();
        }

        promotionDetailsDTO.products = promotion.getProducts().toString();

        return promotionDetailsDTO;
    }

}
