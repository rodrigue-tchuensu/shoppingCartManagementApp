package de.axontic.challenge.shoppingcartmanagement.cart.impl;

import de.axontic.challenge.shoppingcartmanagement.cart.Cart;
import de.axontic.challenge.shoppingcartmanagement.cart.CartRepository;
import de.axontic.challenge.shoppingcartmanagement.cart.CartService;
import de.axontic.challenge.shoppingcartmanagement.customer.Customer;
import de.axontic.challenge.shoppingcartmanagement.customer.CustomerService;
import de.axontic.challenge.shoppingcartmanagement.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CustomerService customerService;

    @Override
    public Cart getCartById(Long id) {
        log.info("Start searching cart with id:{} ...", id);
        Cart cart = cartRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(String.format("Cart with id:%d not found", id)));
        log.info("... end searching cart with id:{} successful", id);

        return cart;
    }

    @Override
    public List<Cart> getAllCartsByCustomerId(Long customerId) {
        log.info("Start searching all carts for customer with id:{} ...", customerId);
        Customer customer = customerService.getCustomerById(customerId);
        List<Cart> carts = cartRepository.findAllByCustomerId(customerId);
        log.info("... end searching all carts for customer with id:{} successful", customerId);
        return carts;
    }

    @Override
    public Cart createCart(Long customerId) {
        log.info("Start creating new cart record ...");
        Customer customer = customerService.getCustomerById(customerId);
        Cart cart = new Cart()
                .setCustomer(customer);
        cart = cartRepository.save(cart);
        log.info("... end creating new  cart record successful");

        return cart;
    }

    public void deleteCartById(Long cartId) {
        log.info("Start deleting cart with id:{} ...", cartId);
        Cart cart = getCartById(cartId);
        cartRepository.delete(cart);
        log.info("... end deleting cart with id:{} ...", cartId);
    }

}
