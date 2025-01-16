package de.axontic.challenge.shoppingcartmanagement.customer.impl;

import de.axontic.challenge.shoppingcartmanagement.customer.Customer;
import de.axontic.challenge.shoppingcartmanagement.customer.CustomerRepository;
import de.axontic.challenge.shoppingcartmanagement.exception.RecordNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCustomerById_ShouldReturnCustomer_WhenIdExists() {
        // Given / Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // When / Act
        Customer result = customerService.getCustomerById(1L);

        // Then / Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(customerRepository).findById(1L);
    }

    @Test
    void getCustomerById_ShouldThrowException_WhenIdDoesNotExist() {
        // Given / Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> customerService.getCustomerById(1L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage("Customer with id:1 not found");
    }
}