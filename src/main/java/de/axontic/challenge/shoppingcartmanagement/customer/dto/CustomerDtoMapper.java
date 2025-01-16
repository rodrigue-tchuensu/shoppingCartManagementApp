package de.axontic.challenge.shoppingcartmanagement.customer.dto;

import de.axontic.challenge.shoppingcartmanagement.customer.Customer;

public class CustomerDtoMapper {

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto()
                .setCustomerId(customer.getId())
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setAddress(customer.getAddress());
    }

    public static Customer fromDto(CustomerDto customerDto) {
        return new Customer()
                .setFirstName(customerDto.getFirstName())
                .setLastName(customerDto.getLastName())
                .setEmail(customerDto.getEmail())
                .setAddress(customerDto.getAddress());
    }
}
