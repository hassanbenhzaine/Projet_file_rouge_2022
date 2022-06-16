package com.youcode.crm.repository;

import com.youcode.crm.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findCustomersByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
}
