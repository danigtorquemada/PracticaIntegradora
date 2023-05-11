package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Promotion;
import com.dgomezt.practicaintegradora.entities.embeddables.Auditory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    @Transactional
    @Modifying
    @Query("update Promotion p set p.auditory.removedDate = :removedDate")
    void logicRemoveAllPromotions(@Param("removedDate") LocalDate removedDate);
}