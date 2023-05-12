package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.entities.helpers.OrderState;
import com.dgomezt.practicaintegradora.repositories.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStateImpl implements OrderStateService{
    @Autowired
    OrderStateRepository orderStateRepository;

    @Override
    public List<OrderState> getPossibleStatesForOrder(Order order) {
        return orderStateRepository.findByIdGreaterThanEqual(order.getOrderState().getId());
    }
}
