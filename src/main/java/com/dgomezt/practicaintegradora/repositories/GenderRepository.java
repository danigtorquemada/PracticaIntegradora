package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.helpers.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}