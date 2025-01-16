package de.axontic.challenge.shoppingcartmanagement.cartitem.impl;

import de.axontic.challenge.shoppingcartmanagement.cart.Cart;
import de.axontic.challenge.shoppingcartmanagement.cart.CartService;
import de.axontic.challenge.shoppingcartmanagement.cartitem.CartItem;
import de.axontic.challenge.shoppingcartmanagement.cartitem.CartItemRepository;
import de.axontic.challenge.shoppingcartmanagement.cartitem.CartItemService;
import de.axontic.challenge.shoppingcartmanagement.cartitem.dto.CartItemDto;
import de.axontic.challenge.shoppingcartmanagement.exception.RecordNotFoundException;
import de.axontic.challenge.shoppingcartmanagement.product.Product;
import de.axontic.challenge.shoppingcartmanagement.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    private final CartService cartService;

    private final ProductService productService;

    @Override
    public CartItem addCartItem(CartItemDto cartItemDto) {
        log.info("Start adding item in cart  ...");

        // Check that the cart exists
        Cart cart = cartService.getCartById(cartItemDto.getCartId());

        // Check that the product exists
        Product product = productService.getProductById(cartItemDto.getProductId());

        // Check if the given product is already in the cart
        Optional<CartItem> cartItemOptional = cartItemRepository.findByCartIdAndProductId(cartItemDto.getCartId(), cartItemDto.getProductId());
        CartItem cartItem;
        if (cartItemOptional.isPresent()) {
            // If the given product is already in the cart then we should update the quantity
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
        }
        else {
            // If the given product is not in the cart, it should be added
            cartItem = new CartItem()
                    .setCart(cart)
                    .setProduct(product)
                    .setQuantity(cartItemDto.getQuantity());
            cart.addCartItem(cartItem);
            cartItem = cartItemRepository.save(cartItem);
        }
        log.info("... end adding  new  item in cart successful");

        return cartItem;
    }


    @Override
    public CartItem updateQuantity(Long cartItemId, Integer quantity) {
        log.info("Start updating item qty in cart  ...");
        CartItem cartItemToUpdate = getCartItemById(cartItemId);
        if (quantity > 0){
            cartItemToUpdate.setQuantity(quantity);
        }
        else {
            cartItemToUpdate = removeCarTItem(cartItemId);
        }

        log.info("... end updating item qty in cart successful");
        return cartItemToUpdate;
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) {
        log.info("Start fetching cartItem with id:{} ...", cartItemId);
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()) {
            log.error("CartItem with id:{} not found", cartItemId);
            throw new RecordNotFoundException(String.format("CartItem with id:%d not found", cartItemId));
        }
        CartItem cartItem  = cartItemOptional.get();
        log.info("... end fetching cartItem with id:{} successful", cartItemId);
        return cartItem;
    }

    @Override
    public CartItem removeCarTItem(Long cartItemId) {
        CartItem cartItemToRemove = getCartItemById(cartItemId);
        Cart cart = cartItemToRemove.getCart();
        cart.removeCartItem(cartItemToRemove);
        return cartItemToRemove;
    }

    @Override
    public Set<CartItem> getAllItemsByCartId(Long cartId) {
        log.info("Start fetching all items by cartId:{}  ...", cartId);
        Cart cart = cartService.getCartById(cartId);
        Set<CartItem> cartItems = cart.getCartItems();
        log.info("... end fetching all items by cartId:{} successful", cartId);
        return cartItems;
    }

}
