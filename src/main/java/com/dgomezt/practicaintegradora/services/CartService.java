package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Cart;
import com.dgomezt.practicaintegradora.entities.dtos.CartDTO;

public interface CartService {
    Cart saveCart(CartDTO cartDTO, Long id);

    Cart createCart(CartDTO cartDTO);
}
