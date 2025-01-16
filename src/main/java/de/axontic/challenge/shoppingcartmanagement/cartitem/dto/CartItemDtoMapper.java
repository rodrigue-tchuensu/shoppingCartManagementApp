package de.axontic.challenge.shoppingcartmanagement.cartitem.dto;

import de.axontic.challenge.shoppingcartmanagement.cartitem.CartItem;

public class CartItemDtoMapper {

    public static CartItemDto toDto(CartItem cartItem) {
        return new CartItemDto()
                .setCartItemId(cartItem.getId())
                .setCartId(cartItem.getCart().getId())
                .setProductId(cartItem.getProduct().getId())
                .setQuantity(cartItem.getQuantity());
    }
}
