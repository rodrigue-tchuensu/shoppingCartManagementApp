package de.axontic.challenge.shoppingcartmanagement.product.impl;

import de.axontic.challenge.shoppingcartmanagement.exception.RecordNotFoundException;
import de.axontic.challenge.shoppingcartmanagement.exception.UnexpectedServerException;
import de.axontic.challenge.shoppingcartmanagement.product.Product;
import de.axontic.challenge.shoppingcartmanagement.product.ProductRepository;
import de.axontic.challenge.shoppingcartmanagement.product.ProductService;
import de.axontic.challenge.shoppingcartmanagement.product.ProductType;
import de.axontic.challenge.shoppingcartmanagement.product.dto.ProductDto;
import de.axontic.challenge.shoppingcartmanagement.product.dto.ProductDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Product getProductById(Long id) {
        log.info("Start searching product with id:{} ...", id);
        Product product = productRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(String.format("Product with id:%d not found", id)));
        log.info("... end searching product with id:{} successful", id);

        return product;
    }

    @Override
    public Page<Product> getAllProducts(int page, int size) {
        log.info("Start fetching all products ...");
        Pageable pagingRequestDetails = PageRequest.of(page, size);
        Page<Product> productsPage = productRepository.findAll(pagingRequestDetails);

        log.info("... end fetching all products");
        return productsPage;
    }

    public Page<Product> getProductsByName(String name, int page, int size) {
        log.info("Start fetching product by name:{} ...", name);
        Pageable pagingRequestDetails = PageRequest.of(page, size);
        Page<Product> productsPage;
        productsPage = productRepository.findAllByNameIgnoreCase(name.trim(), pagingRequestDetails);

        if (productsPage.isEmpty()) {
            productsPage = productRepository.findByNameIgnoreCaseContaining(name.trim(), pagingRequestDetails);
        }

        log.info("... end fetching product by name:{}", name);
        return productsPage;
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        log.info("Start creating new product record ...");
        Product product = ProductDtoMapper.fromDto(productDto);
        product = productRepository.save(product);
        log.info("... end creating new  product record successful");

        return product;
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        log.info("Start updating product with id:{} ...", id);
        Product productToUpdate = getProductById(id);
        Product productUpDated = updateProduct(productToUpdate, productDto);
        log.info("... end updating product with id:{} successful", id);

        return productUpDated;
    }


    @Override
    public Product deleteProduct(Long id) {
        log.info("Start deleting product with id:{} ...", id);
        Product productToDelete = getProductById(id);
        try {
            productRepository.delete(productToDelete);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            log.error("An Error occurred when trying to delete product with id:{}", id);
            throw new UnexpectedServerException(String.format("An error occurred when trying to delete product with id:%d", id));
        }
        log.info("... end deleting product with id:{}", id);

        return productToDelete;
    }

    @Override
    public List<Product> getTop3ProductByProductType(ProductType productType) {
        return productRepository.findTop3ByProductTypeOrderByRatingDesc(productType);
    }

    private Product updateProduct(Product product, ProductDto  productDto) {

        if (productDto.getProductNumber() != null && !productDto.getProductNumber().isEmpty())
            product.setProductNumber(productDto.getProductNumber());

        if (productDto.getName() != null && !productDto.getName().isEmpty())
            product.setName(productDto.getName());

        if (productDto.getPrice() != null)
            product.setPrice(productDto.getPrice());

        if (productDto.getProductType() != null)
            product.setProductType(productDto.getProductType());

        if (productDto.getRating() != null)
            product.setRating(productDto.getRating());

        return product;
    }
}
