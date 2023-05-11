package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.helpers.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientTypeRepository extends JpaRepository<ClientType, Long> {
}