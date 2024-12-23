package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Cart;
import com.dgomezt.practicaintegradora.entities.Client;
import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.entities.ProductCartDetail;
import com.dgomezt.practicaintegradora.entities.dtos.CartDTO;
import com.dgomezt.practicaintegradora.entities.dtos.ProductCartDTO;
import com.dgomezt.practicaintegradora.entities.embeddables.ProductCartKey;
import com.dgomezt.practicaintegradora.repositories.CartRepository;
import com.dgomezt.practicaintegradora.repositories.ClientRepository;
import com.dgomezt.practicaintegradora.repositories.ProductCartDetailRepository;
import com.dgomezt.practicaintegradora.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductCartDetailRepository productCartDetailRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Cart saveCart(CartDTO cartDTO, Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isEmpty()) return null;

        Cart cart = optionalCart.get();

        for (ProductCartDetail productCartDetail : cart.getProductCartDetails()) {
            productCartDetailRepository.deleteByProductCartKey(productCartDetail.getProductCartKey());
        }

        Set<ProductCartDetail> productCartDetails = new HashSet<>();

        for (ProductCartDTO productCartDTO : cartDTO.getProductCartDTOS()) {
            Optional<Product> optionalProduct = productRepository.findById(productCartDTO.getProductId());
            if (optionalProduct.isPresent()){
                Product product = optionalProduct.get();
                ProductCartDetail productCartDetail = new ProductCartDetail();
                productCartDetail.setCart(cart);
                productCartDetail.setProduct(product);
                productCartDetail.setProductCartKey(new ProductCartKey(product.getId(), cart.getId()));
                productCartDetail.setQuantity(productCartDTO.getUnits());

                productCartDetailRepository.save(productCartDetail);

                productCartDetails.add(productCartDetail);
            }
        }
        cart.setProductCartDetails(productCartDetails);

        return cartRepository.save(cart);
    }

    @Override
    public Cart createCart(CartDTO cartDTO) {
        Cart cart = cartRepository.save(new Cart());

        cart.setCreationDate(LocalDate.now());
        cart.setPrice(cartDTO.getPrice());

        Client client = clientRepository.findById(cartDTO.getClient()).get();
        cart.setClient(client);

        Set<ProductCartDetail> productCartDetails = new HashSet<>();

        for (ProductCartDTO productCartDTO : cartDTO.getProductCartDTOS()) {
                Optional<Product> optionalProduct = productRepository.findById(productCartDTO.getProductId());
                if (optionalProduct.isPresent()){
                    Product product = optionalProduct.get();
                    ProductCartDetail productCartDetail = new ProductCartDetail();
                    productCartDetail.setCart(cart);
                    productCartDetail.setProduct(product);
                    productCartDetail.setProductCartKey(new ProductCartKey(product.getId(), cart.getId()));
                    productCartDetail.setQuantity(productCartDTO.getUnits());

                    productCartDetailRepository.save(productCartDetail);

                    productCartDetails.add(productCartDetail);
                }
        }
        cart.setProductCartDetails(productCartDetails);

        return cartRepository.save(cart);
    }

    @Override
    public Cart createCartByClientId(Long idClient) {
        Optional<Client> client = clientRepository.findById(idClient);
        if(client.isEmpty()) return null;

        Cart cart = new Cart();
        cart.setCreationDate(LocalDate.now());
        cart.setClient(client.get());
        cart.setProductCartDetails(new HashSet<>());

        return cartRepository.save(cart);
    }

    @Override
    public Cart getByClientId(Long clientId) {
        return cartRepository.findByClient_Id(clientId);
    }

    @Override
    public Cart addProduct(Long cartId, Long productId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isEmpty()) return null;
        Cart cart = optionalCart.get();

        for (ProductCartDetail productCartDetail : cart.getProductCartDetails()) {
            if(productCartDetail.getProduct().getId() == productId){
                productCartDetail.setQuantity(productCartDetail.getQuantity() + 1);
                return cartRepository.save(cart);
            }
        }
        ProductCartDetail productCartDetail = new ProductCartDetail();
        productCartDetail.setProduct(productRepository.findById(productId).get());
        productCartDetail.setCart(cart);
        productCartDetail.setQuantity(1);
        productCartDetail.setProductCartKey(new ProductCartKey(productId, cartId));

        cart.getProductCartDetails().add(productCartDetailRepository.save(productCartDetail));
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateProduct(Long cartId, Long productId, int amount) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isEmpty()) return null;
        Cart cart = optionalCart.get();

        Set<ProductCartDetail> productCartDetails = new HashSet<>();
        for (ProductCartDetail productCartDetail : cart.getProductCartDetails()) {
            if(productCartDetail.getProduct().getId() == productId){
                productCartDetail.setQuantity(amount);
            }
            if(productCartDetail.getQuantity() != 0)
                productCartDetails.add(productCartDetail);
            else
                productCartDetailRepository.deleteByProductCartKey(productCartDetail.getProductCartKey());
        }

        cart.setProductCartDetails(productCartDetails);

        return cartRepository.save(cart);
    }

    @Override
    public void removeCart(Cart cart) {
        cartRepository.delete(cart);
    }
}
