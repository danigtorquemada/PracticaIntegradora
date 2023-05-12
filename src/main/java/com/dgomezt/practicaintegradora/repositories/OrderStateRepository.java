package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.helpers.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderStateRepository extends JpaRepository<OrderState, Long> {
    List<OrderState> findByIdGreaterThanEqual(Long id);
}