package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    Order findById(Long id) throws ElementNotFoundException;
    void updateStateOrder(Long id, Long newState, UserAdmin userAdmin) throws ElementNotFoundException;
    Order createOrder(Long userId);
}
