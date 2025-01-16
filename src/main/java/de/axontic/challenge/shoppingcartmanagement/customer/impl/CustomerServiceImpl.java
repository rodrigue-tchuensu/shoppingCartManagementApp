package de.axontic.challenge.shoppingcartmanagement.customer.impl;

import de.axontic.challenge.shoppingcartmanagement.customer.Customer;
import de.axontic.challenge.shoppingcartmanagement.customer.CustomerRepository;
import de.axontic.challenge.shoppingcartmanagement.customer.CustomerService;
import de.axontic.challenge.shoppingcartmanagement.customer.dto.CustomerDto;
import de.axontic.challenge.shoppingcartmanagement.customer.dto.CustomerDtoMapper;
import de.axontic.challenge.shoppingcartmanagement.exception.BadRequestException;
import de.axontic.challenge.shoppingcartmanagement.exception.RecordNotFoundException;
import de.axontic.challenge.shoppingcartmanagement.exception.UnexpectedServerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(Long id) {
        log.info("Start searching customer with id:{} ...", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Customer with id:%d not found", id)));
        log.info("... end searching customer with id:{} successful", id);
        return customer;
    }

    @Override
    public Customer createCustomer(CustomerDto customerDto) {
        log.info("Start creating new customer record ...");

        // Check that the new user is not using an email already in use in the system.
        if (customerRepository.existsByEmail(customerDto.getEmail())){
            log.error("Customer creation not possible. Email already exists.");
            throw new BadRequestException("Customer creation not possible. Email already exists.");
        }

        Customer customerCreated = customerRepository.save(CustomerDtoMapper.fromDto(customerDto));
        log.info("... end creating new customer record successful");
        return customerCreated;
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        log.info("Start updating customer with id:{} ...", id);
        Customer customerUpdated = updateCustomer(
                getCustomerById(id),
                customerDto);

        log.info("... end updating customer with id:{} successful", id);
        return customerUpdated;
    }

    @Override
    public Customer deleteCustomerById(Long id) {
        log.info("Start deleting customer with id:{} ...", id);
        Customer customerToDelete = getCustomerById(id);
        try {
            customerRepository.delete(customerToDelete);
        } catch(Exception ex) {
            log.error(ex.getMessage());
            log.error("An Error occurred when trying to delete customer with id:{}", id);
            throw new UnexpectedServerException(String.format("An error occurred when trying to delete customer with id:%d", id));
        }

        log.info("... end deleting customer with id:{}", id);
        return customerToDelete;
    }

    private Customer updateCustomer(Customer customerToUpdate, CustomerDto customerDto) {
        if (customerDto.getFirstName() != null && !customerDto.getFirstName().isEmpty())
            customerToUpdate.setFirstName(customerDto.getFirstName());

        if (customerDto.getLastName() != null && !customerDto.getLastName().isEmpty())
            customerToUpdate.setLastName(customerDto.getLastName());

        if (customerDto.getAddress() != null && !customerDto.getAddress().isEmpty())
            customerToUpdate.setAddress(customerDto.getAddress());

        return customerToUpdate;
    }
}
