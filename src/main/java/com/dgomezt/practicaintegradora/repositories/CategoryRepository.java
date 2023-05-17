package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findByIdIn(Collection<Long> ids);
}