package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.entities.Order;
import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.OrderService;
import com.dgomezt.practicaintegradora.services.OrderStateService;
import com.dgomezt.practicaintegradora.utilities.ConfProperties;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    ConfProperties confProperties;

    @PutMapping("updateState")
    public ResponseEntity<String> updateStateOrder(Long orderId, Long newState, HttpSession httpSession){
        try {
            UserAdmin userAdmin = (UserAdmin) httpSession.getAttribute(confProperties.SESSION_ADMIN_USER);
            orderService.updateStateOrder(orderId, newState, userAdmin);
        } catch (ElementNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Order " + orderId + " updated");
    }
}
