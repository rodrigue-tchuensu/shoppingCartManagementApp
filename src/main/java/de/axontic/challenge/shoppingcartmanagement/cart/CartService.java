package de.axontic.challenge.shoppingcartmanagement.cart;

import java.util.List;

public interface CartService {
    Cart getCartById(Long id);
    List<Cart> getAllCartsByCustomerId(Long customerId);
    Cart createCart(Long customerId);
    void deleteCartById(Long cartId);
}
