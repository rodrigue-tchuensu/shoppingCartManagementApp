package de.axontic.challenge.shoppingcartmanagement.cart;

import de.axontic.challenge.shoppingcartmanagement.cart.dto.CartDto;
import de.axontic.challenge.shoppingcartmanagement.cart.dto.CartDtoMapper;
import de.axontic.challenge.shoppingcartmanagement.cart.dto.CreateCartRequestDto;
import de.axontic.challenge.shoppingcartmanagement.cartitem.CartItemService;
import de.axontic.challenge.shoppingcartmanagement.cartitem.dto.CartItemDto;
import de.axontic.challenge.shoppingcartmanagement.dto.ResponseObject;
import de.axontic.challenge.shoppingcartmanagement.product.Product;
import de.axontic.challenge.shoppingcartmanagement.product.dto.ProductDto;
import de.axontic.challenge.shoppingcartmanagement.product.dto.ProductDtoMapper;
import de.axontic.challenge.shoppingcartmanagement.recommendation.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Endpoints for interacting with carts and their items")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final RecommendationService recommendationService;


    @PostMapping
    @Operation(summary = "Create a cart.")
    public ResponseEntity<ResponseObject<CartDto>> createCart(@RequestBody @Valid CreateCartRequestDto createCartRequestDto) {
        Cart cartCreated = cartService.createCart(createCartRequestDto.getCustomerId());

        HttpStatus httpStatus = HttpStatus.CREATED;
        ResponseObject<CartDto> cartDtoResponseObject = new ResponseObject<>();
        cartDtoResponseObject
                .setStatus(httpStatus.value())
                .setData(CartDtoMapper.toDto(cartCreated))
                .setMessage("Cart created successfully");

        return new ResponseEntity<>(cartDtoResponseObject, httpStatus);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a cart by it's id")
    public ResponseEntity<ResponseObject<CartDto>> getCartById(@PathVariable("id") Long id) {
        Cart cart = cartService.getCartById(id);

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<CartDto> cartDtoResponseObject = new ResponseObject<>();
        cartDtoResponseObject
                .setStatus(httpStatus.value())
                .setData(CartDtoMapper.toDto(cart))
                .setMessage("Cart fetched successfully");

        return new ResponseEntity<>(cartDtoResponseObject, httpStatus);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cart by it's id")
    public ResponseEntity<ResponseObject> deleteCartById(@PathVariable("id") Long cartId) {
        cartService.deleteCartById(cartId);

        HttpStatus httpStatus = HttpStatus.OK;
        var cartResponseObject = new ResponseObject<>();
        cartResponseObject
                .setStatus(httpStatus.value())
                .setMessage("Cart deleted successfully");
        return new ResponseEntity<>(cartResponseObject, httpStatus);
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get all carts and their corresponding cart-items for a given customer_id")
    public ResponseEntity<ResponseObject<List<CartDto>>> getCartsByCustomerId(@PathVariable("customerId") Long customerId){
        List<Cart> carts = cartService.getAllCartsByCustomerId(customerId);
        var cartsDto = carts.parallelStream()
                .map(CartDtoMapper::toDto)
                .collect(Collectors.toList());

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<List<CartDto>> cartsDtoResponseObject = new ResponseObject<>();

        cartsDtoResponseObject
                .setStatus(httpStatus.value())
                .setData(cartsDto)
                .setMessage("Cart fetched successfully");

        return new ResponseEntity<>(cartsDtoResponseObject, httpStatus);
    }



    @PostMapping("/{cardId}/cart-items")
    @Operation(summary = "Add a cart-item to a given cart")
    public ResponseEntity<ResponseObject<CartDto>> addCartItem(@PathVariable("cardId") Long cartId, @RequestBody @Valid CartItemDto cartItemDto) {
        // preventing accidental transmission of different cartId values
        cartItemDto.setCartId(cartId);

        cartItemService.addCartItem(cartItemDto);
        Cart cart = cartService.getCartById(cartId);

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<CartDto> cartDtoResponseObject = new ResponseObject<>();
        cartDtoResponseObject
                .setStatus(httpStatus.value())
                .setData(CartDtoMapper.toDto(cart))
                .setMessage("Item created and added to cart successfully");

        return new ResponseEntity<>(cartDtoResponseObject, httpStatus);
    }

    @PutMapping("/{cardId}/cart-items/{cartItemId}")
    @Operation(summary = "Update a cart-item for a given cart")
    public ResponseEntity<ResponseObject<CartDto>>  updateCartItem(@PathVariable("cardId") Long cartId,
                               @PathVariable("cartItemId") Long cartItemId,
                               @RequestParam("quantity") @Valid @Min(value = 0) Integer qty) {

        cartItemService.updateQuantity(cartItemId, qty);
        Cart cart = cartService.getCartById(cartId);

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<CartDto> cartDtoResponseObject = new ResponseObject<>();
        cartDtoResponseObject
                .setStatus(httpStatus.value())
                .setData(CartDtoMapper.toDto(cart))
                .setMessage("Cart item quantity updated successfully");

        return new ResponseEntity<>(cartDtoResponseObject, httpStatus);
    }

    @GetMapping("/{cartId}/recommendations")
    @Operation(summary = "Get product recommendations for a given cart")
    public ResponseEntity<ResponseObject<List<ProductDto>>> getRecommendationsByCart(@PathVariable("cartId") Long cartId) {
        List<Product> productRecommendations = recommendationService.getProductRecommendationsByCartId(cartId);
        List<ProductDto> productsDto = productRecommendations.stream()
                .map(ProductDtoMapper::toDto)
                .collect(Collectors.toList());

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<List<ProductDto>> productsResponseObject = new ResponseObject<>();
        productsResponseObject
                .setStatus(httpStatus.value())
                .setData(productsDto)
                .setMessage("Recommendations fetched successfully");

        return new ResponseEntity<>(productsResponseObject, httpStatus);
    }



}
