package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.helpers.WarningLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarningLevelRepository extends JpaRepository<WarningLevel, Long> {
}