package de.axontic.challenge.shoppingcartmanagement.product.dto;

import de.axontic.challenge.shoppingcartmanagement.product.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductDto {
    private Long productId;

    @NotBlank(message = "productNumber is required")
    private String productNumber;

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    private BigDecimal price;

    @NotNull(message = "productType is required")
    @Enumerated(EnumType.STRING)
    private ProductType productType;


    @DecimalMin(value = "1.0", message = "rating must be at least 1.0")
    @DecimalMax(value = "5.0", message = "rating must not exceed 5.0")
    @NotNull(message = "rating is required")
    private Double rating;
}
