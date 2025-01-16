package de.axontic.challenge.shoppingcartmanagement.customer.dto;

import de.axontic.challenge.shoppingcartmanagement.customer.Customer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerDtoMapperTest {

    @Test
    public void toDto_sgouldMapCustomertoCustomerDto() {
        // Given / Arrange
        Customer customer = new Customer()
                .setId(1l)
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@mail.de")
                .setAddress("Breitenweg 2, 28329 Bremen");

        CustomerDto expectedCustomerDto = new CustomerDto()
                .setCustomerId(1L)
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@mail.de")
                .setAddress("Breitenweg 2, 28329 Bremen");

        // When / Act
        CustomerDto actualCustomerDto = CustomerDtoMapper.toDto(customer);

        // Then / Assert
        assertThat(actualCustomerDto).isNotNull();
        assertThat(actualCustomerDto).isEqualTo(expectedCustomerDto);

    }

    @Test
    public void fromDto_shouldMapCustomerDtoToCustomer() {
        // Given / Arrange
        CustomerDto customerDto = new CustomerDto()
                .setFirstName("Samuel")
                .setLastName("Eto'o")
                .setEmail("samuel.etoo@mail.de")
                .setAddress("Breitenweg 2, 28329 Bremen");

        // When / Act
        Customer actualCustomerResult = CustomerDtoMapper.fromDto(customerDto);

        // Then / Assert
        assertThat(actualCustomerResult).isNotNull();
        assertThat(actualCustomerResult.getFirstName()).isEqualTo("Samuel");
        assertThat(actualCustomerResult.getLastName()).isEqualTo("Eto'o");
        assertThat(actualCustomerResult.getEmail()).isEqualTo("samuel.etoo@mail.de");
        assertThat(actualCustomerResult.getAddress()).isEqualTo("Breitenweg 2, 28329 Bremen");
    }
}