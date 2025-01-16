package de.axontic.challenge.shoppingcartmanagement.product;

import de.axontic.challenge.shoppingcartmanagement.dto.PagedResult;
import de.axontic.challenge.shoppingcartmanagement.dto.ResponseObject;
import de.axontic.challenge.shoppingcartmanagement.product.dto.ProductDto;
import de.axontic.challenge.shoppingcartmanagement.product.dto.ProductDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Endpoints for interacting with the product resource")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by it's id")
    public ResponseEntity<ResponseObject<ProductDto>> getById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        ProductDto productDto = ProductDtoMapper.toDto(product);

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<ProductDto> productDtoResponseObject = new ResponseObject<>();
        productDtoResponseObject
                .setStatus(httpStatus.value())
                .setData(productDto)
                .setMessage("Product fetched successfully");

        return new ResponseEntity<>(productDtoResponseObject, httpStatus);
    }

    @GetMapping
    @Operation(summary = "Get all products. The products can also be fetched by 'name'. The name query parameter is optional though")
    public ResponseEntity<ResponseObject<PagedResult<ProductDto>>> getAllProduct(@RequestParam("name") Optional<String> nameOptional,
                                                                                 @RequestParam(defaultValue = "0", name = "page") int page,
                                                                                 @RequestParam(defaultValue = "20", name = "size") int size) {
        Page<Product> productsPage;

        if (nameOptional.isPresent()) {
            productsPage = productService.getProductsByName(nameOptional.get(), page, size);
        } else {
            productsPage = productService.getAllProducts(page, size);
        }

        PagedResult<ProductDto> productDtoPagedResult = new PagedResult<>();
        productDtoPagedResult
                .setCurrentPage(productsPage.getNumber())
                .setTotalPages(productsPage.getTotalPages())
                .setTotalItems(productsPage.getTotalElements())
                .setData(productsPage.getContent()
                        .parallelStream()
                        .map(ProductDtoMapper::toDto)
                        .collect(Collectors.toList()));

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<PagedResult<ProductDto>> pagedResultResponseObject = new ResponseObject<>();
        pagedResultResponseObject
                .setStatus(httpStatus.value())
                .setData(productDtoPagedResult)
                .setMessage("Products fetched successfully");

        return new ResponseEntity<>(pagedResultResponseObject, httpStatus);
    }

    @PostMapping
    @Operation(summary = "Create a product record using the data from the request payload")
    public ResponseEntity<ResponseObject<ProductDto>> create(@RequestBody @Validated ProductDto productDto) {
        Product productCreated = productService.createProduct(productDto);

        HttpStatus httpStatus = HttpStatus.CREATED;
        ResponseObject<ProductDto> productDtoResponseObject = new ResponseObject<>();
        productDtoResponseObject
                .setStatus(httpStatus.value())
                .setData(ProductDtoMapper.toDto(productCreated))
                .setMessage("Product created successfully");

        return new ResponseEntity<>(productDtoResponseObject, httpStatus);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product")
    public ResponseEntity<ResponseObject<ProductDto>> update(@PathVariable("id") Long id, @RequestBody @Valid ProductDto productDto) {
        Product productUpdated = productService.updateProduct(id, productDto);

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<ProductDto> productDtoResponseObject = new ResponseObject<>();
        productDtoResponseObject
                .setStatus(httpStatus.value())
                .setData(ProductDtoMapper.toDto(productUpdated))
                .setMessage("Product updated successfully");

        return new ResponseEntity<>(productDtoResponseObject, httpStatus);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by it's id")
    public ResponseEntity<ResponseObject<ProductDto>> delete(@PathVariable("id") Long id) {
        Product productDeleted = productService.deleteProduct(id);

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<ProductDto> productDtoResponseObject = new ResponseObject<>();
        productDtoResponseObject
                .setStatus(httpStatus.value())
                .setData(ProductDtoMapper.toDto(productDeleted))
                .setMessage("Product deleted successfully");

        return new ResponseEntity<>(productDtoResponseObject, httpStatus);
    }



}
