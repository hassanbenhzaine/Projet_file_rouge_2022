package com.youcode.crm.service;

import com.youcode.crm.entity.Customer;
import com.youcode.crm.entity.dto.CustomerDTO;
import com.youcode.crm.mapper.CustomerMapper;
import com.youcode.crm.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements CurrentTimeInterface{

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    
    public List<Customer> addNewCustomers(Iterable<Customer> customers){
        return customerRepository.saveAll(customers);
    }
    
    /**
     * The method is to retrieve all customers from the database and display them.
     *
     * After downloading all the data about the customer,
     * the data is mapped to dto which will display only those needed
     * @return list of all customers with specification of data in CustomerDTO
     */
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers(Pageable pageable){
        return customerRepository.findAll(pageable)
                .stream()
                .map(customerMapper::mapCustomerToDto)
                .collect(Collectors.toList());
    }

    /**
     * The method is to download a specific customer from the database and display it.
     * After downloading all the data about the customer,
     * the data is mapped to dto which will display only those needed
     *
     * @param id id of the customer to be searched for
     * @throws ResponseStatusException if the id of the customer you are looking for does not exist throws 404 status
     * @return detailed data about a specific customer
     */
    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer cannot be found, the specified id does not exist"));
        return customerMapper.mapCustomerToDto(customer);

    }

    /**
     * The method is to retrieve customers whose have the firstname specified by the user.
     * After downloading all the data about the customer,
     * the data is mapped to dto which will display only those needed
     * @param firstName firstname of the customer
     * @return details of specific customers
     */
    @Transactional(readOnly = true)
    public List<CustomerDTO> findCustomersByFirstName(String firstName, Pageable pageable){
        return customerRepository.findCustomersByFirstNameContainingIgnoreCase(firstName, pageable)
                .stream()
                .map(customerMapper::mapCustomerToDto)
                .collect(Collectors.toList());
    }

    /**
     * The task of the method is to add a customer to the database.
     * @param customer requestbody of the customer to be saved
     * @return saving the customer to the database
     */
    public CustomerDTO addNewCustomer(CustomerDTO customer) {
        Customer addedCustomer = customerRepository.save(customerMapper.mapCustomerDTOtoCustomer(customer));
        return customerMapper.mapCustomerToDto(addedCustomer);
    }

    /**
     * Method deletes the selected customer by id
     * @param id id of the customer to be deleted
     * @throws ResponseStatusException if id of the customer is incorrect throws 404 status with message
     */
    public void deleteCustomerById(Long id) {
        try{
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The specified id does not exist");
        }
    }

    @Transactional
    public CustomerDTO editCustomer(CustomerDTO customer) {
        Customer mappedCustomer = customerMapper.mapCustomerDTOtoCustomer(customer);
        Customer editedCustomer = customerRepository.findById(mappedCustomer.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer does not exist"));
        editedCustomer.setFirstName(mappedCustomer.getFirstName());
        editedCustomer.setLastName(mappedCustomer.getLastName());
        editedCustomer.setCity(mappedCustomer.getCity());
        editedCustomer.setZipCode(mappedCustomer.getZipCode());
        editedCustomer.setCin(mappedCustomer.getCin());
        return customerMapper.mapCustomerToDto(editedCustomer);
    }
}
