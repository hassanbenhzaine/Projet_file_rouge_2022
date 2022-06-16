package com.youcode.crm.mapper;

import com.youcode.crm.entity.Employee;
import com.youcode.crm.entity.dto.EmployeeDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings({
            @Mapping(target = "role", source = "userRole"),
            @Mapping(target = "departmentId", source = "department.id"),
            @Mapping(target = "departmentName", source = "department.name")
    })
    EmployeeDTO mapEmployeeToDto(Employee employee);

    @InheritInverseConfiguration
    Employee mapEmployeeDtoToEmployee(EmployeeDTO employeeDTO);
}
