package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByClient_Id(Long id);
}