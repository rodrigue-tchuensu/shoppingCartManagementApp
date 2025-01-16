package de.axontic.challenge.shoppingcartmanagement.cart.dto;

import de.axontic.challenge.shoppingcartmanagement.cart.Cart;
import de.axontic.challenge.shoppingcartmanagement.cartitem.dto.CartItemDtoMapper;

import java.util.stream.Collectors;

public class CartDtoMapper {

    public static CartDto toDto(Cart cart) {
        var cartItemsDto = cart.getCartItems()
                .parallelStream()
                .map(CartItemDtoMapper::toDto)
                .collect(Collectors.toSet());

        return new CartDto()
                .setCartId(cart.getId())
                .setCustomerId(cart.getCustomer().getId())
                .setCartItemsDto(cartItemsDto);
    }
}
