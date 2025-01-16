package de.axontic.challenge.shoppingcartmanagement.cart.dto;

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
public class CreateCartRequestDto {
    @NotNull(message = "customerId is required")
    private Long customerId;
}
