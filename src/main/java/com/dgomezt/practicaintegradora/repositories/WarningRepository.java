package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Warning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarningRepository extends JpaRepository<Warning, Long> {
}