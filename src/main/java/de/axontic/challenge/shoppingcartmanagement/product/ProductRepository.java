package de.axontic.challenge.shoppingcartmanagement.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByNameIgnoreCase(String productName, Pageable pageable);
    Page<Product> findByNameIgnoreCaseContaining(String productName, Pageable pageable);
    List<Product> findTop3ByProductTypeOrderByRatingDesc(ProductType productType);
}
