package de.axontic.challenge.shoppingcartmanagement.product;

import de.axontic.challenge.shoppingcartmanagement.product.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);
    Page<Product>  getAllProducts(int page, int size);
    Page<Product> getProductsByName(String name, int page, int size);
    List<Product> getTop3ProductByProductType(ProductType productType);
    Product createProduct(ProductDto productDto);
    Product updateProduct(Long id, ProductDto productDto);
    Product deleteProduct(Long id);
}
