package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
}