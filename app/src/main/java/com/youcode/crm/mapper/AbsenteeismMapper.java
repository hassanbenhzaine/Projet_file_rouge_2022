package com.youcode.crm.mapper;

import com.youcode.crm.entity.Absenteeism;
import com.youcode.crm.entity.dto.AbsenteeismDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AbsenteeismMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "employeeId", source = "employee.id"),
            @Mapping(target = "employeeFirstName", source = "employee.firstName"),
            @Mapping(target = "employeeLastName", source = "employee.lastName"),
            @Mapping(target = "employeeDepartmentName", source = "employee.department.name"),
            @Mapping(target = "absenteeismReasonId", source = "reasonOfAbsenteeismCode.id"),
            @Mapping(target = "absenteeismReasonName", source = "reasonOfAbsenteeismCode.name")
    })
    AbsenteeismDTO mapAbsenteeismToDto(Absenteeism absenteeism);

    @InheritInverseConfiguration
    Absenteeism mapAbsenteeismDtoToAbsenteeism(AbsenteeismDTO absenteeismDTO);
}
