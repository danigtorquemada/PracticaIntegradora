package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.helpers.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}