package de.axontic.challenge.shoppingcartmanagement.cartitem;

import de.axontic.challenge.shoppingcartmanagement.cartitem.dto.CartItemDto;

import java.util.Set;

public interface CartItemService {

    CartItem addCartItem(CartItemDto cartItemDto);
    CartItem updateQuantity(Long cartItemId, Integer quantity);
    CartItem getCartItemById(Long cartItemId);
    CartItem removeCarTItem(Long cartItemId);

    Set<CartItem> getAllItemsByCartId(Long cartId);
}
