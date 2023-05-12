package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
