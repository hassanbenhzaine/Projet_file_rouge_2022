package com.youcode.crm.mapper;

import com.youcode.crm.entity.Customer;
import com.youcode.crm.entity.dto.CustomerDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO mapCustomerToDto(Customer customer);

    Customer mapCustomerDTOtoCustomer(CustomerDTO customerDTO);
}
