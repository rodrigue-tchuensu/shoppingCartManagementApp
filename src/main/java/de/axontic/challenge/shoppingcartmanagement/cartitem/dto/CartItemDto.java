package de.axontic.challenge.shoppingcartmanagement.cartitem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CartItemDto {
    private Long cartItemId;

    @NotNull
    private Long cartId;

    @NotNull
    private Long productId;

    @NotNull
    @Min(value = 1, message = "quantity should be at least of value 1")
    private Integer quantity;
}
