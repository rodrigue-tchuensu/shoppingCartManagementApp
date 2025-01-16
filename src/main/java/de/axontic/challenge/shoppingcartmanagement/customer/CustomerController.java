package de.axontic.challenge.shoppingcartmanagement.customer;

import de.axontic.challenge.shoppingcartmanagement.customer.dto.CustomerDto;
import de.axontic.challenge.shoppingcartmanagement.customer.dto.CustomerDtoMapper;
import de.axontic.challenge.shoppingcartmanagement.dto.ResponseObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Endpoints for interacting with the customer resource")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    @Operation(summary = "Get a customer by it's id")
    public ResponseEntity<ResponseObject<CustomerDto>> getById(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<CustomerDto> customerResponseObject = new ResponseObject<>();
        customerResponseObject
                .setStatus(httpStatus.value())
                .setData(CustomerDtoMapper.toDto(customer))
                .setMessage("Customer fetched successfully");

        return new ResponseEntity<>(customerResponseObject, httpStatus);
    }

    @PostMapping
    @Operation(summary = "Create a customer using the data from the request payload")
    public ResponseEntity<ResponseObject<CustomerDto>> create(@RequestBody @Valid CustomerDto customerDto) {
        Customer customerCreated = customerService.createCustomer(customerDto);

        HttpStatus httpStatus = HttpStatus.CREATED;
        ResponseObject<CustomerDto> customerResponseObject = new ResponseObject<>();
        customerResponseObject
                .setStatus(httpStatus.value())
                .setData(CustomerDtoMapper.toDto(customerCreated))
                .setMessage("Customer record created successfully");

        return new ResponseEntity<>(customerResponseObject, httpStatus);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a customer, only the firstName, lastName and address properties can be updated")
    public ResponseEntity<ResponseObject<CustomerDto>> update(@PathVariable("id") Long id, @RequestBody @Valid CustomerDto customerDto) {
        Customer customerUpdated = customerService.updateCustomer(id, customerDto);

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<CustomerDto> customerResponseObject = new ResponseObject<>();
        customerResponseObject
                .setStatus(httpStatus.value())
                .setData(CustomerDtoMapper.toDto(customerUpdated))
                .setMessage("Customer record updated successfully");

        return new ResponseEntity<>(customerResponseObject, httpStatus);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer by it's id")
    public ResponseEntity<ResponseObject<CustomerDto>> delete(@PathVariable("id") Long id) {
        Customer customerDeleted = customerService.deleteCustomerById(id);

        HttpStatus httpStatus = HttpStatus.OK;
        ResponseObject<CustomerDto> customerResponseObject = new ResponseObject<>();
        customerResponseObject
                .setStatus(httpStatus.value())
                .setData(CustomerDtoMapper.toDto(customerDeleted))
                .setMessage("Customer deleted successfully");

        return new ResponseEntity<>(customerResponseObject, httpStatus);
    }
}
