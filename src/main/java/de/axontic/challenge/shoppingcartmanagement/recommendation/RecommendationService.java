package de.axontic.challenge.shoppingcartmanagement.recommendation;

import de.axontic.challenge.shoppingcartmanagement.product.Product;

import java.util.List;

public interface RecommendationService {

    List<Product> getProductRecommendationsByCartId(Long cartId);
}
