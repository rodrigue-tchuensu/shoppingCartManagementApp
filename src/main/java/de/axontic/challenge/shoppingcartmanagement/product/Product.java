package de.axontic.challenge.shoppingcartmanagement.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "productNumber is required")
    private String productNumber;

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    private BigDecimal price;

    @NotNull(message = "productType is required")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @NotNull(message = "rating is required")
    private Double rating;
}
