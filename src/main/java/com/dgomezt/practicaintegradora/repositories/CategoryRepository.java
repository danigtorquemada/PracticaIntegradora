package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
