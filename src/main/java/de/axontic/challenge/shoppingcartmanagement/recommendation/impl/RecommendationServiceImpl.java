package de.axontic.challenge.shoppingcartmanagement.recommendation.impl;

import de.axontic.challenge.shoppingcartmanagement.cart.Cart;
import de.axontic.challenge.shoppingcartmanagement.cart.CartService;
import de.axontic.challenge.shoppingcartmanagement.product.Product;
import de.axontic.challenge.shoppingcartmanagement.product.ProductService;
import de.axontic.challenge.shoppingcartmanagement.product.ProductType;
import de.axontic.challenge.shoppingcartmanagement.recommendation.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final CartService cartService;
    private final ProductService productService;

    @Override
    public List<Product> getProductRecommendationsByCartId(Long cartId) {
        Cart cart = cartService.getCartById(cartId);

        Set<ProductType> productTypesInCart = cart.getCartItems()
                .stream()
                .map(cartItem -> cartItem.getProduct().getProductType())
                .collect(Collectors.toSet());

        return productTypesInCart.stream()
                .flatMap(productType -> productService.getTop3ProductByProductType(productType).stream())
                .collect(Collectors.toList());
    }
}
