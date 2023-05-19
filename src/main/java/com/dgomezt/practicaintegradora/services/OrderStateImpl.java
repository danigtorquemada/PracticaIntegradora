package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.entities.helpers.OrderState;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStateImpl implements OrderStateService{
    @Autowired
    OrderStateRepository orderStateRepository;

    @Override
    public List<OrderState> getPossibleStatesForOrder(Order order) {
        return orderStateRepository.findByIdGreaterThanEqual(order.getOrderState().getId());
    }

    @Override
    public OrderState findById(Long id) throws ElementNotFoundException {
        Optional<OrderState> optionalOrderState = orderStateRepository.findById(id);
        if(optionalOrderState.isEmpty()) throw new ElementNotFoundException("Order state not found in bbdd");
        return optionalOrderState.get();
    }
}
