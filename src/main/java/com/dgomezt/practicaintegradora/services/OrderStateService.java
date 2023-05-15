package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.entities.helpers.OrderState;

import java.util.List;

public interface OrderStateService {
    public List<OrderState> getPossibleStatesForOrder(Order order);
}