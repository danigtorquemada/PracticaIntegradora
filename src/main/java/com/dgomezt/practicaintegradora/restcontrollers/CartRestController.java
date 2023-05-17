package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.entities.Cart;
import com.dgomezt.practicaintegradora.entities.dtos.CartDTO;
import com.dgomezt.practicaintegradora.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartRestController {
    @Autowired
    CartService cartService;
    @PostMapping("/save")
    public ResponseEntity<Cart> createCart(@RequestBody CartDTO cartDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCart(cartDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cart> updateCart(@RequestBody CartDTO cartDTO, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.saveCart(cartDTO, id));
    }
}
