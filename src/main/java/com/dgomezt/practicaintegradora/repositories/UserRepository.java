package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}