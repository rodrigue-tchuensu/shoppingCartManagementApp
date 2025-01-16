package de.axontic.challenge.shoppingcartmanagement.cart;

import de.axontic.challenge.shoppingcartmanagement.cartitem.CartItem;
import de.axontic.challenge.shoppingcartmanagement.customer.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "customerId is required")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    public void addCartItem(CartItem item) {
        this.cartItems.add(item);
        item.setCart(this);
    }

    public void removeCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
        cartItem.setCart(null);
    }
}
