package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.entities.helpers.OrderState;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.OrderRepository;
import com.dgomezt.practicaintegradora.repositories.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) throws ElementNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isEmpty()) throw new ElementNotFoundException("Order with id " + id + " not found");

        return optionalOrder.get();
    }

    @Override
    public void updateStateOrder(Long id, Long newState) throws ElementNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isEmpty()) throw new ElementNotFoundException("Order with id " + id + " not found");

        OrderState newOrderState = new OrderState();
        newOrderState.setId(newState);
        orderRepository.updateOrderStateById(newOrderState, id);
    }
}
