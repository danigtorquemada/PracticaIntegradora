package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.*;
import com.dgomezt.practicaintegradora.entities.embeddables.ProductOrderKey;
import com.dgomezt.practicaintegradora.entities.helpers.OrderState;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderStateService orderStateService;
    @Autowired
    CartService cartService;
    @Autowired
    ClientService clientService;
    @Autowired
    ProductOrderDetailService productOrderDetailService;

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

    @Override
    public Order createOrder(Long userId) {
        Client client = clientService.getByUserId(userId);
        Cart cart = cartService.getByClientId(client.getId());

        Order order = new Order();
        order.setClient(client);
        order.setDate(Date.from(Instant.now()));
        try {
            order.setOrderState(orderStateService.findById(1L));
        } catch (ElementNotFoundException e) {
            throw new RuntimeException(e);
        }
        Order newOrder = orderRepository.save(order);

        double price = 0;
        for (ProductCartDetail productCartDetail : cart.getProductCartDetails()) {
            ProductOrderDetails productOrderDetails = new ProductOrderDetails();
            productOrderDetails.setOrderId(newOrder);
            productOrderDetails.setProduct(productCartDetail.getProduct());
            productOrderDetails.setPrice(Double.valueOf(productCartDetail.getProduct().getPrice().toString()));
            productOrderDetails.setQuantity(productCartDetail.getQuantity());
            productOrderDetails.setProductOrderKey(new ProductOrderKey(newOrder.getId(), productCartDetail.getProduct().getId()));
            newOrder.getProductOrderDetailses().add(productOrderDetailService.save(productOrderDetails));

            price += productOrderDetails.getPrice() * productOrderDetails.getQuantity();
        }

        newOrder.setTotalPrice(BigDecimal.valueOf(price));

        return orderRepository.save(newOrder);
    }
}
