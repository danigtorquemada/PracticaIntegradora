package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}