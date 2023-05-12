package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.OrderService;
import com.dgomezt.practicaintegradora.services.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderRestController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderStateService orderStateService;

    @PutMapping("updateState")
    public ResponseEntity<String> updateStateOrder(Long orderId, Long newState){
        try {
            orderService.updateStateOrder(orderId, newState);
        } catch (ElementNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Order " + orderId + " updated");
    }
}
