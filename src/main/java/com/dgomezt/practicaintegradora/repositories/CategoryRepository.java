package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}