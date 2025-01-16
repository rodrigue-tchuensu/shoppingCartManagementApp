package de.axontic.challenge.shoppingcartmanagement.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CustomerDto {

    private Long customerId;

    @Size(max = 70, message = "firstName cannot be more than 70 character long")
    @NotBlank(message = "firstName is required")
    private String firstName;

    @Size(max = 70, message = "lastName cannot be more than 70 character long")
    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "address is required")
    private String address;
}
