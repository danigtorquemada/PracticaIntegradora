package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.entities.Cart;
import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.dtos.CartDTO;
import com.dgomezt.practicaintegradora.services.CartService;
import com.dgomezt.practicaintegradora.services.ClientService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("cart")
public class CartRestController {
    @Autowired
    CartService cartService;
    @Autowired
    ClientService clientService;

    @PostMapping("/save")
    public ResponseEntity<Cart> createCart(@RequestBody CartDTO cartDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCart(cartDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cart> updateCart(@RequestBody CartDTO cartDTO, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.saveCart(cartDTO, id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<CartDTO> getCartUser(@PathVariable String id, HttpSession session){
        Client client = clientService.getByUserId(Long.valueOf(id));

        Cart cart = cartService.getByClientId(client.getId());
        if(cart == null)
            cart = cartService.createCartByClientId(client.getId());

        CartDTO cartDTO = new CartDTO(cart);
        return ResponseEntity.ok(cartDTO);
    }

    @PutMapping("/user/{userId}/addproduct/{productId}")
    public ResponseEntity<CartDTO> addProduct(@PathVariable long userId, @PathVariable long productId){
        Client client = clientService.getByUserId(Long.valueOf(userId));

        Cart cart = cartService.getByClientId(client.getId());
        if(cart == null)
            cart = cartService.createCartByClientId(client.getId());

        cart = cartService.addProduct(cart.getId(), productId);
        CartDTO cartDTO = new CartDTO(cart);

        return ResponseEntity.ok(cartDTO);
    }
}
