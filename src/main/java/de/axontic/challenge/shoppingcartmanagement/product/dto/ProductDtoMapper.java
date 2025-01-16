package de.axontic.challenge.shoppingcartmanagement.product.dto;

import de.axontic.challenge.shoppingcartmanagement.product.Product;

public class ProductDtoMapper {

    public static Product fromDto(ProductDto productDto) {

        return new Product()
                .setProductNumber(productDto.getProductNumber())
                .setName(productDto.getName())
                .setPrice(productDto.getPrice())
                .setProductType(productDto.getProductType())
                .setRating(productDto.getRating());
    }

    public static ProductDto toDto(Product product) {

        return new ProductDto()
                .setProductId(product.getId())
                .setProductNumber(product.getProductNumber())
                .setName(product.getName())
                .setPrice(product.getPrice())
                .setProductType(product.getProductType())
                .setRating(product.getRating());
    }
}
