package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.entities.helpers.OrderState;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderStateService orderStateService;

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
    public void updateStateOrder(Long id, Long newState, UserAdmin userAdmin) throws ElementNotFoundException {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if(optionalOrder.isEmpty()) throw new ElementNotFoundException("Order with id " + id + " not found");
        Order order = optionalOrder.get();
        OrderState newOrderState = orderStateService.findById(newState);
        order.setOrderState(newOrderState);
        order.setUserAdmin(userAdmin);

        orderRepository.save(order);
    }
}
