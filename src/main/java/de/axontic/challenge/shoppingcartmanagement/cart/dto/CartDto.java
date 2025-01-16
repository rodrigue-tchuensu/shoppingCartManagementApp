package de.axontic.challenge.shoppingcartmanagement.cart.dto;

import de.axontic.challenge.shoppingcartmanagement.cartitem.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CartDto {

    private Long cartId;
    private Long customerId;
    private Set<CartItemDto> cartItemsDto;
}
