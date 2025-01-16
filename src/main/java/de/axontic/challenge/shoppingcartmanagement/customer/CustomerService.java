package de.axontic.challenge.shoppingcartmanagement.customer;

import de.axontic.challenge.shoppingcartmanagement.customer.dto.CustomerDto;

public interface CustomerService {

    //List<Customer> getAll();
    Customer getCustomerById(Long id);
    Customer createCustomer(CustomerDto customerDto);
    Customer updateCustomer(Long id, CustomerDto customerDto);
    Customer deleteCustomerById(Long id);
}
