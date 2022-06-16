package com.youcode.crm.controller;

import com.youcode.crm.entity.Customer;
import com.youcode.crm.entity.dto.CustomerDTO;
import com.youcode.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youcode.crm.controller.ApiMapping.CUSTOMERS_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(CUSTOMERS_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(Pageable pageable){
        return status(HttpStatus.OK).body(customerService.getAllCustomers(pageable));
    }

    @GetMapping(path = "/{id}", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable final Long id) {
        return status(HttpStatus.OK).body(customerService.getCustomerById(id));
    }

    @GetMapping(path = "/", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<CustomerDTO>> findCustomersByFirstName(
            @RequestParam final String firstName, Pageable pageable){
        return status(HttpStatus.OK).body(customerService.findCustomersByFirstName(firstName, pageable));
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces= APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<CustomerDTO> editCustomer(@RequestParam Long id, @RequestBody final CustomerDTO customer){
        return status(HttpStatus.OK).body(customerService.editCustomer(customer));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces= APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<CustomerDTO> postNewCustomer(@RequestBody final CustomerDTO customer) {
        return status(HttpStatus.OK).body(customerService.addNewCustomer(customer));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCustomerById(@PathVariable final Long id) {
        customerService.deleteCustomerById(id);
    }

}
